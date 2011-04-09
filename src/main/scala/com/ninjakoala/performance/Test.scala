package com.ninjakoala.performance

object Test {
    def apply(name: String, samples: Iterable[Sample]) = {
        new Test(name, samples)
    }
}

class Test(val name: String, val samples: Iterable[Sample]) {
    override def toString = {
        "Test(" + name + ",Samples(" + samples.mkString(",") + "))"
    }
}