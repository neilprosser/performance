package com.ninjakoala.performance

import cc.spray._

trait PerformanceServiceBuilder extends ServiceBuilder {

    val Name = "[^/]+".r
    val Description = "[^/]+".r

    val performanceService = {

        path("runs" / Name / Description) { (name, description) =>
            get { _.complete("You looking for " + name + " and " + description + "?") }
        }

    }

}