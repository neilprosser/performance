package com.ninjakoala.performance

trait RunStore {
    def getRun(name: String, description: String): Option[Run]
    def saveRun(run: Run)
}