package com.ninjakoala.performance

import cc.spray._
import http._
import HttpStatusCodes._

trait PerformanceServiceBuilder extends ServiceBuilder with CustomMarshallers with CustomUnmarshallers {

    val runStore: RunStore

    val Name = "[^/]+".r
    val Description = "[^/]+".r

    val performanceService = {
        path("runs") {
            get {
                _.complete(getAllRunsFromStore())
            }
        } ~
        path("run" / Name / Description) { (runName, runDescription) =>
            get {
                getRunFromStore(runName, runDescription) match {
                    case Some(r) => _.complete(r)
                    case None => _.fail(NotFound, "Run '" + runName + "/" + runDescription + "' could not be found")
                }
            }
        } ~
        path("run" / Name / Description / "test" / Name) { (runName, runDescription, testName) =>
        	get {
        		getTestFromStore(runName, runDescription, testName) match {
        			case Some(t) => _.complete(t)
        			case None => _.fail(NotFound, "Test '" + testName + "' from run '" + runName + "/" + runDescription + "' could not be found")
                }
        	} ~
            put {
        	    contentAs[JtlContent] { jtl => 
                    detached {
                        saveTestToStore(runName, runDescription, testName, jtl)
                        _.complete("Test saved")
                    }
                }
            }
        }
    }
    
    def getAllRunsFromStore() = {
        runStore.getRuns()
    }
    
    def getRunFromStore(runName: String, runDescription: String) = {
        runStore.getRun(runName, runDescription)
    }
    
    def getTestFromStore(runName: String, runDescription: String, testName: String) = {
        runStore.getTest(runName, runDescription, testName)
    }
    
    def saveTestToStore(runName: String, runDescription: String, testName: String, jtlContent: JtlContent) = {
        runStore.saveTest(runName, runDescription, testName, jtlContent)
    }

}
