package com.ninjakoala.performance

import cc.spray._

trait PerformanceServiceBuilder extends ServiceBuilder with RunMarshallers {
    
    def runStore: RunStore

    val Name = "[^/]+".r
    val Description = "[^/]+".r

    val performanceService = {
        path("runs" / Name / Description) { (name, description) =>
            get { _.complete(getRunFromStore(name, description)) }
        }
    }
    
    def getRunFromStore(name: String, description: String) = {
        runStore.getRun(name, description)
    }

}