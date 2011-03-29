package code.model

import net.liftweb.record.field._
import net.liftweb.mongodb.record._
import net.liftweb.mongodb.record.field._

class RunDoc private () extends MongoRecord[RunDoc] with MongoId[RunDoc] {
	
	def meta = RunDoc
	
	object name extends StringField(this, 128)
	object description extends StringField(this, 32)
	object tests extends DBRefField[RunDoc, TestDoc](this, TestDoc)
	object timestamp extends DateTimeField(this)
	
}

object RunDoc extends RunDoc with MongoMetaRecord[RunDoc] {
}

class TestDoc private () extends MongoRecord[TestDoc] with MongoId[TestDoc] {
	
	def meta = TestDoc
	
	object name extends StringField(this, 128)
	
}

object TestDoc extends TestDoc with MongoMetaRecord[TestDoc] {
}

class SampleDoc private () extends MongoRecord[SampleDoc] with MongoId[SampleDoc] {
	
	def meta = SampleDoc
	
}

object SampleDoc extends SampleDoc with MongoMetaRecord[SampleDoc] {
}

class PropertyDoc private () extends MongoRecord[PropertyDoc] with MongoId[PropertyDoc] {
	
	def meta = PropertyDoc
	
	object name extends StringField(this, 32)
	object value extends StringField(this, 256)
	
}

object PropertyDoc extends PropertyDoc with MongoMetaRecord[PropertyDoc] {
}