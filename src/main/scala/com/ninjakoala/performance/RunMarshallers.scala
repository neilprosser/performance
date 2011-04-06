package com.ninjakoala.performance

import cc.spray._
import http._
import marshalling._
import MediaTypes._

trait RunMarshallers extends DefaultMarshallers {

    this: PerformanceServiceBuilder =>

    implicit object RunMarshaller extends MarshallerBase[Run] {
        
        val canMarshalTo = ContentType(`application/json`) :: ContentType(`text/xml`) :: Nil

        def marshal(value: Run, contentType: ContentType) = contentType match {
            case x @ ContentType(`application/json`, _) => HttpContent(x, marshalToJson(value))
            case x @ ContentType(`text/xml`, _) => HttpContent(x, "<run name=\"" + value.name + "\" description=\"" + value.description + "\" />")
            case _ => throw new IllegalArgumentException
        }

        def marshalToJson(value: Run) = {
            "{ name:\"" + value.name + "\", description:\"" + value.description + "\" }"
        }
        
    }
}