package com.ninjakoala.performance

import org.specs.Specification
import cc.spray._
import test._
import http._
import HttpMethods._
import HttpHeaders._
import MediaTypes._
import HttpStatusCodes._

class PerformanceServiceBuilderSpec extends Specification
	with SprayTest with PerformanceServiceBuilder with DontDetach {
    
    def runStore = new RunStore {
        def getRun(name: String, description: String) = {
        	Run(name, description)
        }
    }
    
    "The performance service" should {
	    "get by name and description" in {
	    	testService(HttpRequest(GET, "/runs/name/description", HttpHeader("Content-Type", "application/json") :: Nil)) {
	    	    performanceService
	    	}.response.content mustEqual Some(HttpContent(`application/json`, "{ name:\"name\", description:\"description\" }"))
	    }
    }
    
}
