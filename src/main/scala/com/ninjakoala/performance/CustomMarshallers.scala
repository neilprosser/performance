package com.ninjakoala.performance

import cc.spray._
import http._
import marshalling._
import MediaTypes._
import net.liftweb.json._
import net.liftweb.json.JsonDSL._

trait CustomMarshallers extends DefaultMarshallers {
    
    this: PerformanceServiceBuilder =>
    
    implicit object RunsMarshaller extends MarshallerBase[Iterable[Run]] {
        
        val canMarshalTo = ContentType(`application/json`) :: Nil
        
        def marshal(value: Iterable[Run], contentType: ContentType) = contentType match {
            case x @ ContentType(`application/json`, _) => HttpContent(x, compact(render(marshalToJson(value))))
            case _ => throw new IllegalArgumentException
        }
        
        def marshalToJson(value: Iterable[Run]) = {
            ("runs" -> value.map { r =>
            	"/runs/" + r.name + "/" + r.description 
            })
        }
        
    }

    implicit object RunMarshaller extends MarshallerBase[Run] {
        
        val canMarshalTo = ContentType(`application/json`) :: Nil

        def marshal(value: Run, contentType: ContentType) = contentType match {
            case x @ ContentType(`application/json`, _) => HttpContent(x, compact(render(marshalToJson(value))))
            case _ => throw new IllegalArgumentException
        }

        def marshalToJson(run: Run) = {
            ("name" -> run.name) ~
            ("description" -> run.description) ~
            ("tests" -> run.tests.map { t =>
            	"/runs/" + run.name + "/" + run.description + "/test/" + t.name
            })
        }
        
    }
    
    implicit object TestMarshaller extends MarshallerBase[Test] {
        
        val canMarshalTo = ContentType(`application/json`) :: Nil
        
        def marshal(value: Test, contentType: ContentType) = contentType match {
            case x @ ContentType(`application/json`, _) => HttpContent(x, compact(render(marshalToJson(value))))
            case _ => throw new IllegalArgumentException
        }
        
        def marshalToJson(test: Test) = {
            ("name" -> test.name) ~
            ("samples" -> test.samples.map { s =>
            	("properties" -> s.properties.map { p =>
            		("name" -> p.name) ~
            		("value" -> p.value)
            	})
            })
        }
        
    }
    
}