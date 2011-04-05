package com.ninjakoala.performance

import org.specs.Specification
import cc.spray._
import test._
import http._
import HttpMethods._
import HttpHeaders._
import MediaTypes._
import HttpStatusCodes._

class PerformanceServiceBuilderSpec extends Specification with SprayTest with PerformanceServiceBuilder {
    
    "The performance service" should {
	    "get by name and description" in {
	    	testService(HttpRequest(GET, "/runs/name/description")) {
	    	    performanceService
	    	}.response.content.as[String] mustEqual Right("You looking for name and description?")
	    }
    }
    
}