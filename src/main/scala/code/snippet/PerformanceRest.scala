package code.snippet

import net.liftweb.common._
import net.liftweb.util.Helpers._
import net.liftweb.http._
import net.liftweb.http.rest._
import net.liftweb.json.JsonDSL._
import net.liftweb.json.JsonAST._

import code.model._

object PerformanceRest extends RestHelper {
	
	serve {
		
		case "runs" :: id :: Nil JsonGet _ => {
			for {
				r <- RunDoc.find(id) ?~ "Run not found"
			} yield r.asJSON
		}
		
		case "runs" :: name :: description :: Nil JsonGet _ => {
			for {
				r <- RunDoc.find(("name" -> name)~("description" -> description)) ?~ "Run not found"
			} yield r.asJSON
		}
		
		case "runs" :: Nil JsonPut json -> _ => {
			
			val run = for {
				JField("name", JString(name)) <- json
				JField("description", JString(description)) <- json
			} yield RunDoc.createRecord.name(name).description(description)
			
			run.head.save
			run.head.asJSON
		}
		
	}
  
}
