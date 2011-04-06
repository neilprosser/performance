package com.ninjakoala.performance

object Run {
    def apply(name: String, description: String) = {
        new Run(name, description)
    }
}

class Run(val name: String, val description: String) {
    override def toString = "Run(" + name + "," + description + ")"
}