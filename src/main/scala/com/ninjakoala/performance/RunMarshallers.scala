package com.ninjakoala.performance

import cc.spray._
import http._
import marshalling._
import MediaTypes._

trait RunMarshallers extends DefaultMarshallers {

    this: PerformanceServiceBuilder =>

    implicit object RunMarshaller extends MarshallerBase[Run] {
        val canMarshalTo = List(ContentType(`application/json`), ContentType(`text/plain`))

        def marshal(value: Run, contentType: ContentType) = contentType match {
            case x @ ContentType(`application/json`, _) => HttpContent(x, marshalToJson(value))
            case x @ ContentType(`text/plain`, _) => HttpContent(x, "Argh!")
            case _ => throw new IllegalArgumentException
        }

        def marshalToJson(value: Run) = {
            "{ name:\"" + value.name + "\", description:\"" + value.description + "\" }"
        }
    }

}