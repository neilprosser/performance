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
        val runs = scala.collection.mutable.Map.empty[String, Run]
        def getRun(name: String, description: String) = {
            runs.get(name + "-" + description)
        }
        def createRun(name: String, description: String) = {
            runs += (name + "-" + description -> Run(name, description))
        }
    }

    "The performance service" should {
        "not find a run when it doesn't exist" in {
            testService(HttpRequest(GET, "/runs/name/description")) {
                performanceService
            }.response mustEqual failure(NotFound, "Run with name: name and description: description could not be found")
        }
        "get by name and description for JSON" in {
            testService(HttpRequest(GET, "/runs/name/description", headers = List(`Accept`(`application/json`)))) {
                performanceService
            }.response.content mustEqual Some(HttpContent(`application/json`, "{ name:\"name\", description:\"description\" }"))
        }
        "get by name and description for XML" in {
            testService(HttpRequest(GET, "/runs/name/description", headers = List(`Accept`(`text/xml`)))) {
                performanceService
            }.response.content mustEqual Some(HttpContent(`text/xml`, "<woot />"))
        }
    }

}
