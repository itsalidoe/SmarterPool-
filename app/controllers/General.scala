package controllers

import controllers.general.Question
import play.api.libs.json._
import play.api.mvc._
import play.api.templates.Html
import utilities.ContentReader

/**
 * Created with IntelliJ IDEA.
 * User: sean
 * Date: 11/12/13
 * Time: 12:58 AM
 * To change this template use File | Settings | File Templates.
 */

object General extends Controller {
  def faq = Action {
    implicit val format = Json.format[Question]
    val faq = ContentReader.ReadJson[List[Question]]("conf/content/general/FAQ", List.empty)
    Ok(views.html.base("companies.")(new Html(new StringBuilder))(views.html.faq(faq))("faq"))
  }

  def contact = Action {
    Ok(views.html.base("companies.")(new Html(new StringBuilder))(views.html.contact())("contact"))
  }
}
