package com.ninjakoala.performance

import akka.config.Supervision._
import akka.actor.Supervisor
import akka.actor.Actor._
import cc.spray._
import utils.ActorHelpers._

class Boot {

    val mainModule = new PerformanceServiceBuilder {
        val runStore = new HashMapRunStore()
    }

    Supervisor(
        SupervisorConfig(
            OneForOneStrategy(List(classOf[Exception]), 3, 100),
            List(
                Supervise(actorOf[RootService], Permanent))))

    actor[RootService] ! Attach(HttpService(mainModule.performanceService))

}