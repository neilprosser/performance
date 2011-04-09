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
            testService(HttpRequest(GET, "/run/runName/runDescription")) {
                performanceService
            }.response mustEqual failure(NotFound, "Run 'runName/runDescription' could not be found")
        }
        "not find a test when it doesn't exist" in {
            testService(HttpRequest(GET, "/run/runName/runDescription/test/testName")) {
                performanceService
            }.response mustEqual failure(NotFound, "Test 'testName' from run 'runName/runDescription' could not be found")
        }
        "fail when attempting to add a test with invalid XML" in {
            testService(HttpRequest(PUT, "/run/runName/runDescription/test/testName",
                    headers = List(`Content-Type`(`text/xml`)),
                    content = Some(HttpContent(ContentType(`text/xml`), "<foo bar>")))) {
                performanceService
            }.response mustEqual failure(BadRequest, "XML content was invalid")
        }
        "fail when attempting to add a test with valid XML but invalid JTL structure" in {
            testService(HttpRequest(PUT, "/run/runName/runDescription/test/testName",
                    headers = List(`Content-Type`(`text/xml`)),
                    content = Some(HttpContent(ContentType(`text/xml`), "<foo />")))) {
                performanceService
            }.response mustEqual failure(BadRequest, "JTL content was invalid")
        }
        "succeed when attempting to add a test with valid JTL structure" in {
            testService(HttpRequest(PUT, "/run/runName/runDescription/test/testName",
                    headers = List(`Content-Type`(`text/xml`)),
                    content = Some(HttpContent(ContentType(`text/xml`), "<testResults><httpSample s='true' /></testResults>")))) {
                performanceService
            }.response.content mustEqual Some(HttpContent(`text/plain`, "Test saved"))
        }
        "find a test when it exists" in {
            testService(HttpRequest(PUT, "/run/runName/runDescription/test/testName",
                    headers = List(`Content-Type`(`text/xml`)),
                    content = Some(HttpContent(ContentType(`text/xml`), "<testResults><httpSample s='true' /></testResults>")))) {
                performanceService
            }
            testService(HttpRequest(GET, "/run/runName/runDescription/test/testName",
                    headers = List(`Accept`(`application/json`)))) {
                performanceService
            }.response.content mustEqual Some(HttpContent(`application/json`, "{\"name\":\"testName\",\"samples\":[{\"properties\":[{\"name\":\"s\",\"value\":\"true\"}]}]}"))
        }
        "not find a test when the run exists but the test doesn't" in {
            testService(HttpRequest(PUT, "/run/runName/runDescription/test/otherTestName",
                    headers = List(`Content-Type`(`text/xml`)),
                    content = Some(HttpContent(ContentType(`text/xml`), "<testResults><httpSample s='true' /></testResults>")))) {
                performanceService
            }
            testService(HttpRequest(GET, "/run/runName/runDescription/test/testName",
                    headers = List(`Accept`(`application/json`)))) {
                performanceService
            }.response mustEqual failure(NotFound, "Test 'testName' from run 'runName/runDescription' could not be found")
        }
        "find a run when it has had a test added" in {
            testService(HttpRequest(PUT, "/run/runName/runDescription/test/testName",
                    headers = List(`Content-Type`(`text/xml`)),
                    content = Some(HttpContent(ContentType(`text/xml`), "<testResults><httpSample s='true' /></testResults>")))) {
                performanceService
            }
            testService(HttpRequest(GET, "/run/runName/runDescription",
                    headers = List(`Accept`(`application/json`)))) {
                performanceService
            }.response.content mustEqual Some(HttpContent(ContentType(`application/json`), "{\"name\":\"runName\",\"description\":\"runDescription\",\"tests\":[\"/runs/runName/runDescription/test/testName\"]}"))
        }
        "append a new test to a run" in {
            testService(HttpRequest(PUT, "/run/runName/runDescription/test/testName1",
                    headers = List(`Content-Type`(`text/xml`)),
                    content = Some(HttpContent(ContentType(`text/xml`), "<testResults><httpSample s='true' /></testResults>")))) {
                performanceService
            }
            testService(HttpRequest(PUT, "/run/runName/runDescription/test/testName2",
                    headers = List(`Content-Type`(`text/xml`)),
                    content = Some(HttpContent(ContentType(`text/xml`), "<testResults><httpSample s='false' /></testResults>")))) {
                performanceService
            }
            testService(HttpRequest(GET, "/run/runName/runDescription",
                    headers = List(`Accept`(`application/json`)))) {
                performanceService
            }.response.content mustEqual Some(HttpContent(ContentType(`application/json`), "{\"name\":\"runName\",\"description\":\"runDescription\",\"tests\":[\"/runs/runName/runDescription/test/testName1\",\"/runs/runName/runDescription/test/testName2\"]}"))
        }
    }

}
