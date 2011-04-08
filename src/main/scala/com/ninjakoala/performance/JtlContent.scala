package com.ninjakoala.performance

class JtlContent(val samples: Iterable[Sample]) {
    override def toString = {
        "Jtl(" + samples.mkString(",") + ")"
    }
}

object Sample {
    def apply(properties: Iterable[Property]) = {
        new Sample(properties)
    }
}

class Sample(val properties: Iterable[Property]) {
    override def toString = {
        "Sample(" + properties.mkString(",") + ")"
    }
}

object Property {
    def apply(name: String, value: String) = {
        new Property(name, value)
    }
}

class Property(val name: String, val value: String) {
    override def toString = {
        "Property(" + name + "," + value + ")"
    }
}