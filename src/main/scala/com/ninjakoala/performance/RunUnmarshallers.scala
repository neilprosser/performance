package com.ninjakoala.performance

import cc.spray._
import http._
import marshalling._
import MediaTypes._

trait RunUnmarshallers extends DefaultUnmarshallers {
    
    this: PerformanceServiceBuilder =>
    
    implicit object RunUnmarshaller extends UnmarshallerBase[Run] {
        
        val canUnmarshalFrom = ContentTypeRange(`application/json`) :: ContentTypeRange(`text/xml`) :: Nil
    
	    def unmarshal(content: HttpContent) = content.contentType match {
            case x @ ContentType(`application/json`, _) => Right(Run("json", "json"))
            case x @ ContentType(`text/xml`, _) => Right(Run("xml", "xml"))
	    	case _ => throw new IllegalStateException
        }
        
    }

}