package com.ninjakoala.performance

sealed case class Test(name: String, samples: List[Sample]) {
}