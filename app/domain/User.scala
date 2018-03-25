package domain

import play.api.libs.json.Json


case class User(name: String) {

}
object User{
  implicit val jsonFormat=Json.format[User]
}
