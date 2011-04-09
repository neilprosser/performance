package com.ninjakoala.performance

trait RunStore {
    def getRuns(): Iterable[Run]
    def getRun(runName: String, runDescription: String): Option[Run]
    def getTest(runName: String, runDescription: String, testName: String): Option[Test]
    def saveTest(runName: String, runDescription: String, testName: String, jtlContent: JtlContent)
}