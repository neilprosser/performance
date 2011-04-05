package com.ninjakoala.performance

trait RunStore {
    
    def getRun(name: String, description: String): Run
    def createRun(run: Run)
    def deleteRun(name: String, description: String)
    
}