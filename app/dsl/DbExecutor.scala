package dsl

import javax.inject.Singleton

import slick.jdbc.H2Profile.api._

import scala.concurrent.Future

@Singleton
class DbExecutor {

  def execute(callback: () => DBIOAction[_, NoStream, Nothing]): Future[_] = {
    val db = Database.forConfig("h2mem1")
    try {
      db.run(callback.apply())
    }
    finally db.close
  }
}
