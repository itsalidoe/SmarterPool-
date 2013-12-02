package controllers

import play.api.mvc._
import play.api.templates.Html

object Status extends Controller {

  def success = Ok(views.html.base("companies.")(new Html(new StringBuilder))(views.html.success())(""))
  def failure = Ok(views.html.base("companies.")(new Html(new StringBuilder))(views.html.failure())(""))

  def Success = Action {
    success
  }

  def Failure = Action {
    failure
  }
}