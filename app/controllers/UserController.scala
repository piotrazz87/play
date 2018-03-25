package controllers

import javax.inject.{Inject, Singleton}

import domain.User
import dsl.CoffeeDao
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._
import services.CoffeeService


@Singleton
class UserController @Inject()(cc: MessagesControllerComponents,
                               coffeeService: CoffeeService) extends MessagesAbstractController(cc) {
  val form = Form(mapping("name" -> text)(User.apply)(User.unapply))

  def show =
    Action { implicit request: MessagesRequest[AnyContent] =>
      Ok(views.html.users(form.fill(User("Marlenka"))))
    }

  def create = Action { implicit request => {
    coffeeService.putCofee

    val changedForm = form.bindFromRequest()
    coffeeService.getAll.foreach(println(_))
    Ok(views.html.users(changedForm))
  }
  }
}
