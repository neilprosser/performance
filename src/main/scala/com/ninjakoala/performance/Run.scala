package com.ninjakoala.performance

object Run {
    def apply(name: String, description: String, tests: Iterable[Test]) = {
        new Run(name, description, tests)
    }
}

class Run(val name: String, val description: String, val tests: Iterable[Test]) {
    override def toString = "Run(" + name + "," + description + ")"
}