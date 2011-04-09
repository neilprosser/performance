package com.ninjakoala.performance

import cc.spray._
import http._
import marshalling._
import MediaTypes._
import HttpStatusCodes._
import xml.NodeSeq

trait CustomUnmarshallers extends DefaultUnmarshallers {
    
    this: PerformanceServiceBuilder =>
    
    implicit object JtlUnmarshaller extends UnmarshallerBase[JtlContent] {
        
        val canUnmarshalFrom = ContentTypeRange(`text/xml`) :: Nil
        
        def unmarshal(content: HttpContent) = content.contentType match {
            case x @ ContentType(`text/xml`, _) => NodeSeqUnmarshaller.unmarshal(content) match {
                case Right(ns) => unmarshalFromNodeSeq(ns)
                case _ => throw HttpException(BadRequest, "XML content was invalid")
            }
            case _ => throw new IllegalStateException
        }
        
        def unmarshalFromNodeSeq(nodeSeq : NodeSeq) = {
            
            val httpSamples = (nodeSeq \ "httpSample")
            
            httpSamples.isEmpty match {
                case true => throw HttpException(BadRequest, "JTL content was invalid")
                case false => {
                    val samples = httpSamples.map(s => Sample(s.attributes.map(a => Property(a.key, a.value.toString))))
                    Right(new JtlContent(samples))
                }
            }
            
        }
        
    }

}
