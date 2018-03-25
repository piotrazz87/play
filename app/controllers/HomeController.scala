package controllers

import javax.inject._

import play.api.libs.json.{JsObject, JsString}
import play.api.mvc._

import scala.util.parsing.json.JSONObject

@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index = Action {
    Ok(views.html.index("Witam!"))
  }

}

