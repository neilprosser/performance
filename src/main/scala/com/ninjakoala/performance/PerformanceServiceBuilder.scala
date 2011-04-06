package com.ninjakoala.performance

import cc.spray._
import http._
import HttpStatusCodes._

trait PerformanceServiceBuilder extends ServiceBuilder with RunMarshallers with RunUnmarshallers {

    val runStore: RunStore

    val Name = "[^/]+".r
    val Description = "[^/]+".r

    val performanceService = {
        path("runs" / Name / Description) { (name, description) =>
            get {
                getRunFromStore(name, description) match {
                    case Some(r) => _.complete(r)
                    case None => _.fail(NotFound, "Run with name: " + name + " and description: " + description + " could not be found")
                }
            } ~
            put {
                contentAs[Run] { run => 
                	detached { ctx =>
                		saveRun(run)
                	    ctx.complete("Run saved")
                	}
                	
                }
            }
        }
    }
    
    def getRunFromStore(name: String, description: String) = {
        runStore.getRun(name, description)
    }
    
    def saveRun(run: Run) = {
        runStore.saveRun(run)
    }

}
