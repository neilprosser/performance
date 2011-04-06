package com.ninjakoala.performance

trait RunStore {
    def getRun(name: String, description: String): Option[Run]
    def createRun(name: String, description: String)
}