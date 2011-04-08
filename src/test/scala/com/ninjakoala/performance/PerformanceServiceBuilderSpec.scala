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

    val runStore = new HashMapRunStore()

    "The performance service" should {
        "not find a run when it doesn't exist" in {
            testService(HttpRequest(GET, "/runs/name/description")) {
                performanceService
            }.response mustEqual failure(NotFound, "Run with name: name and description: description could not be found")
        }
        "get by name and description for XML" in {
            testService(HttpRequest(PUT, "/runs/xml/xml",
                    headers = List(`Content-Type`(`text/xml`)),
        	        content = Some(HttpContent(ContentType(`text/xml`), "<woot />")))) {
                performanceService
            }
            testService(HttpRequest(GET, "/runs/xml/xml",
                    headers = List(`Accept`(`text/xml`)))) {
                performanceService
            }.response.content mustEqual Some(HttpContent(`text/xml`, "<run name=\"xml\" description=\"xml\" />"))
        }
    }

}
