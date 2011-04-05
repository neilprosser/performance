package com.ninjakoala.performance

import org.specs.Specification
import cc.spray._
import test._
import http._
import HttpMethods._
import HttpHeaders._
import MediaTypes._
import HttpStatusCodes._

class PerformanceServiceBuilderSpec extends Specification with SprayTest with PerformanceServiceBuilder with DontDetach {
    
    def runStore = new RunStore {
        
        val runs = collection.mutable.Map.empty[String, Run]
        
        def getRun(name: String, description: String) = {
            runs(name + "-" + description)
        }
        def createRun(run: Run) {
            runs += (run.name + "-" + run.description -> run)
        }
        def deleteRun(name: String, description: String) {
            runs -= name + "-" + description
        }
        
    }
    
    "The performance service" should {
	    /*"get by name and description" in {
	    	testService(HttpRequest(GET, "/runs/name/description", HttpHeader("Content-Type", "application/json") :: Nil)) {
	    	    performanceService
	    	}.response.content.as[String] mustEqual Right("{ name: \"name\", description: \"description\" }")
	    }*/
    }
    
}
