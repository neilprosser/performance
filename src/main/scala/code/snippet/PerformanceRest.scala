package performance

import net.liftweb.http._
import net.liftweb.http.rest._
import net.liftweb.json.JsonAST._

object PerformanceRest extends RestHelper {

  serve {
	  
    case Req("runs" :: id, "json", GetRequest) => {
    	JObject(Nil)
    }
    
    case Req("tests" :: id, "json", GetRequest) => {
    	JObject(Nil)
    }
    
  }
  
}
