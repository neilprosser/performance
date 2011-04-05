package com.ninjakoala.performance

import akka.config.Supervision._
import akka.actor.Supervisor
import akka.actor.Actor._
import cc.spray._
import utils.ActorHelpers._

class Boot {

    val mainModule = new PerformanceServiceBuilder {
        def runStore = new RunStore {
            def getRun(name: String, description: String) = {
                Run("name", "description", Nil)
            }
            def createRun(run: Run) {
                
            }
            def deleteRun(name: String, description: String) {
                
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