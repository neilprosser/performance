package com.ninjakoala.performance

sealed case class Run(name: String, description: String, tests: List[Test]) {
}