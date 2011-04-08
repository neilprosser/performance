package com.ninjakoala.performance

class HashMapRunStore extends RunStore {
    
    val runs = scala.collection.mutable.Map.empty[String, Run]
    
    def getRuns() = {
        runs.values
    }
    
    def getRun(runName: String, runDescription: String) = {
        runs.get(createKey(runName, runDescription))
    }
    
    def getTest(runName: String, runDescription: String, testName: String) = {
        None
    }
    
    def saveRun(run: Run) = {
        runs += (createKey(run.name, run.description) -> run)
    }
    
    private def createKey(runName: String, runDescription: String) = {
        runName + "-" + runDescription
    }
    
}