package com.ninjakoala.performance

trait RunStore {
    def getRuns(): Iterable[Run]
    def getRun(runName: String, runDescription: String): Option[Run]
    def getTest(runName: String, runDescription: String, testName: String): Option[Test]
    def saveRun(run: Run)
}