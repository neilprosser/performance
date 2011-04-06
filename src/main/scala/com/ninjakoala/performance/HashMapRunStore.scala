package com.ninjakoala.performance

class HashMapRunStore extends RunStore {
    
    val runs = scala.collection.mutable.Map.empty[String, Run]
    
    def getRun(name: String, description: String) = {
        runs.get(name + "-" + description)
    }
    
    def saveRun(run: Run) = {
        runs += (run.name + "-" + run.description -> run)
    }
    
}