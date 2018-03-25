package services

import javax.inject.{Inject, Singleton}

import dsl.CoffeeDao

@Singleton
class CoffeeService @Inject()(coffeeDao: CoffeeDao) {

  def putCofee: Unit = {
    coffeeDao.insert()
  }

  def getAll: List[(String, Int, Double, Int, Int)] = {
    coffeeDao.findByName("Colombian")
  }
}
