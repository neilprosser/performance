package com.ninjakoala.performance

import akka.config.Supervision._
import akka.actor.Supervisor
import akka.actor.Actor._
import cc.spray._
import utils.ActorHelpers._

class Boot {

    val mainModule = new PerformanceServiceBuilder {
        def runStore = new RunStore {
            val runs = scala.collection.mutable.Map.empty[String, Run]
	        def getRun(name: String, description: String) = {
	            runs.get(name + "-" + description)
	        }
	        def createRun(name: String, description: String) = {
	            runs += (name + "-" + description -> Run(name, description))
	        }
        }
    }

    // start the root service actor (and any service actors you want to specify supervision details for)
    Supervisor(
        SupervisorConfig(
            OneForOneStrategy(List(classOf[Exception]), 3, 100),
            List(
                Supervise(actorOf[RootService], Permanent)
            )
        )
    )

    // attach an HttpService (which is also an actor)
    // the root service automatically starts the HttpService if it is unstarted
    actor[RootService] ! Attach(HttpService(mainModule.performanceService))

}