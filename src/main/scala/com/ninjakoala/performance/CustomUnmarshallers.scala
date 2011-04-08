package com.ninjakoala.performance

import cc.spray._
import http._
import marshalling._
import MediaTypes._
import xml.NodeSeq

trait CustomUnmarshallers extends DefaultUnmarshallers {
    
    this: PerformanceServiceBuilder =>
    
    implicit object JtlUnmarshaller extends UnmarshallerBase[JtlContent] {
        
        val canUnmarshalFrom = ContentTypeRange(`text/xml`) :: Nil
        
        def unmarshal(content: HttpContent) = content.contentType match {
            case x @ ContentType(`text/xml`, _) => NodeSeqUnmarshaller.unmarshal(content) match {
                case Right(ns) => Right(unmarshalFromNodeSeq(ns))
                case _ => throw new IllegalStateException
            }
            case _ => throw new IllegalStateException
        }
        
        def unmarshalFromNodeSeq(nodeSeq : NodeSeq) = {
            val samples = (nodeSeq \\ "httpSample").map(s => Sample(s.attributes.map(a => Property(a.key, a.value.toString))))
            new JtlContent(samples)
        }
        
    }

}