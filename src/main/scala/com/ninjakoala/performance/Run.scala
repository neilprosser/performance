package com.ninjakoala.performance

object Run {
    def apply(name: String, description: String, tests: List[Test]) = {
        new Run(name, description, tests)
    }
}

class Run(val name: String, val description: String, val tests: List[Test]) {
    override def toString = "Run(" + name + "," + description + ")"
}