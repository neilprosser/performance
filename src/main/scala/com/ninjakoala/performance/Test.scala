package com.ninjakoala.performance

object Test {
    def apply(name: String, samples: List[Sample]) = {
        new Test(name, samples)
    }
}

class Test(val name: String, val samples: List[Sample]) {
}