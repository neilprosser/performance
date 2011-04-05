package com.ninjakoala.performance

trait RunStore {
    def getRun(name: String, description: String): Run
}