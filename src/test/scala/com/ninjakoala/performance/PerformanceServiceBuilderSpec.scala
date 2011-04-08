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
        "succeed when getting all runs when none exist" in {
            testService(HttpRequest(GET, "/runs")) {
                performanceService
            }.response.content mustEqual Some(HttpContent(`application/json`, "{\"runs\":[]}"))
        }
        "not find a run when it doesn't exist" in {
            testService(HttpRequest(GET, "/run/name/description")) {
                performanceService
            }.response mustEqual failure(NotFound, "Run 'name/description' could not be found")
        }
        "not find a test when it doesn't exist" in {
            testService(HttpRequest(GET, "/run/name/description/test/name")) {
                performanceService
            }.response mustEqual failure(NotFound, "Test 'name' from run 'name/description' could not be found")
        }
    }

}
