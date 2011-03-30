package code.snippet

import net.liftweb.http._
import net.liftweb.http.rest._
import net.liftweb.json.DefaultFormats
import net.liftweb.json.JsonDSL._
import net.liftweb.json.JsonAST._

import com.mongodb._

import code.model._

object PerformanceRest extends RestHelper {

	serve {
		
		case "runs" :: id :: _ JsonGet _ => {
			RunDoc.find(id)
			JObject(Nil)
		}
		
		case "runs" :: _ JsonPut _ => {
			val run = RunDoc.createRecord
			run.save
			JObject(JField("id", run.id.toString) :: Nil)
		}
		
	}
  
}
