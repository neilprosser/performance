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
        getRun(runName, runDescription) match {
            case Some(r) => r.tests.find(_.name == testName)
            case _ => None
        }
    }
    
    def saveTest(runName: String, runDescription: String, testName: String, jtlContent: JtlContent) = {
        val test = List(Test(testName, jtlContent.samples))
        val tests = getRun(runName, runDescription) match {
            case Some(r) => r.tests ++ test
            case _ => test
        }
        
        runs += (createKey(runName, runDescription) -> Run(runName, runDescription, tests))
    }
    
    private def createKey(runName: String, runDescription: String) = {
        runName + "-" + runDescription
    }
    
}