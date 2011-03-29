package code {
package snippet {

import net.liftweb.http._
import net.liftweb.http.rest._
import net.liftweb.json.JsonAST._

import code.model._

import org.bson.types._

object PerformanceRest extends RestHelper {

  serve {
	  
    case Req("runs" :: id :: Nil, "json", GetRequest) => {
    	JObject(RunDoc.find(id.toString).map(r => JField("name", JString(r.name.toString))).toList)
    }
    
    case Req("runs" :: Nil, "json", PostRequest) => {
    	val run = RunDoc.createRecord
    	run.save
    	JObject(JField("id", JString(run.id.toString)) :: Nil)
    }
    
    case Req("tests" :: name, "json", GetRequest) => {
    	JObject(Nil)
    }
    
  }
  
}

}
}