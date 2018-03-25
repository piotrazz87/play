package dsl

import com.google.inject.AbstractModule
import domain.Schema
import slick.jdbc.H2Profile.api._

class Module extends AbstractModule {
  override def configure {
   val db= Database.forConfig("h2mem1")
      db.run(DBIO.seq((Schema.coffees.schema ++ Schema.suppliers.schema).create))
  }
}
