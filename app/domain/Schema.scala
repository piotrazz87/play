package domain

import slick.jdbc.H2Profile.api._

class Schema {


}

class Coffees(tag: Tag) extends Table[(String, Int, Double, Int, Int)](tag, "COFFEES") {
  def name = column[String]("COF_NAME", O.PrimaryKey)

  def supID = column[Int]("SUP_ID")

  def price = column[Double]("PRICE")

  def sales = column[Int]("SALES")

  def total = column[Int]("TOTAL")

  def * = (name, supID, price, sales, total)

  def supplier = foreignKey("SUP_FK", supID, Schema.suppliers)(_.id)
}

class Suppliers(tag: Tag) extends Table[(Int, String, String, String, String, String)](tag, "SUPPLIERS") {
  def id = column[Int]("SUP_ID", O.PrimaryKey)

  def name = column[String]("SUP_NAME")

  def street = column[String]("STREET")

  def city = column[String]("CITY")

  def state = column[String]("STATE")

  def zip = column[String]("ZIP")

  def * = (id, name, street, city, state, zip)
}

object Schema {
  val coffees = TableQuery[Coffees]
  val suppliers = TableQuery[Suppliers]
}