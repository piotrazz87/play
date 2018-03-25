package dsl

import javax.inject.{Inject, Singleton}

import domain.Schema
import slick.jdbc.H2Profile.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.util.{Failure, Success}

@Singleton
class CoffeeDao @Inject()(executor: DbExecutor) {

  def insert(): Future[Any] = {
    executor.execute(() => DBIO.seq(Schema.suppliers += (101, "Acme, Inc.", "99 Market Street", "Groundsville", "CA", "95199"),
      Schema.suppliers += (49, "Superior Coffee", "1 Party Place", "Mendocino", "CA", "95460"),
      Schema.suppliers += (150, "The High Ground", "100 Coffee Lane", "Meadows", "CA", "93966"),
      Schema.coffees ++= Seq(
        ("Colombian", 101, 7.99, 0, 0),
        ("French_Roast", 49, 8.99, 0, 0),
        ("Espresso", 150, 9.99, 0, 0),
        ("Colombian_Decaf", 101, 8.99, 0, 0),
        ("French_Roast_Decaf", 49, 9.99, 0, 0)
      )))
  }

  def findByName(coffeeName: String): List[(String, Int, Double, Int, Int)] = {
    val db = Database.forConfig("h2mem1")
    db.run(DBIO.seq((Schema.coffees.schema ++ Schema.suppliers.schema).create))
    insert()
    val results = db.run(Schema.coffees.result)
      .map(_.filter({
        case (name, _, _, _, _) => coffeeName equals name
      }))
    Await.result(results, Duration.Inf).toList
  }
}
