package code.model

import net.liftweb.record.field._
import net.liftweb.mongodb.record._
import net.liftweb.mongodb.record.field._

class RunDoc private () extends MongoRecord[RunDoc] with MongoId[RunDoc] {
	
	def meta = RunDoc
	
	object name extends StringField(this, 128)
	object description extends StringField(this, 32)
	object timestamp extends DateTimeField(this)
	
}

object RunDoc extends RunDoc with MongoMetaRecord[RunDoc] {
}
