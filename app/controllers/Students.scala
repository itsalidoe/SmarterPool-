package controllers

import play.api.mvc._
import controllers.bootstrap.{Features, Marketing, Carousel, Featurette}
import models.Student;
import org.mindrot.jbcrypt.BCrypt
import utilities.ContentReader
;

/**
 * Created with IntelliJ IDEA.
 * User: sean
 * Date: 17/11/13
 * Time: 7:32 PM
 * To change this template use File | Settings | File Templates.
 */

object Students extends MarketingPage {

  def index = Action {
    val carousel = Carousel("conf/content/students/CarouselFeatures")
    val marketing = Marketing("conf/content/students/CarouselFeatures", 2)
    val howItWorksMarketing = Marketing("conf/content/students/HowItWorksFeatures", 3)
    val largeMarketing = LargeMarketing("conf/content/students/LargeMarketingFeatures")
    val universities = ContentReader.ReadJson[List[String]]("conf/content/students/Universities", List.empty)
    val majors = ContentReader.ReadJson[List[String]]("conf/content/students/Majors", List.empty)

    Ok(views.html.base("Students.")(carousel.Html)(marketing.Html + howItWorksMarketing.Html + largeMarketing.Html + views.html.signup.student_signup(universities, majors))("students"))
  }

  def signUp = Action { request =>
    val fullName = request.body.asFormUrlEncoded.get("fullName") mkString ""
    val password = request.body.asFormUrlEncoded.get("password") mkString ""
    val hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt())
    val school = request.body.asFormUrlEncoded.get("school") mkString ""
    val email = request.body.asFormUrlEncoded.get("email") mkString ""
    val major = request.body.asFormUrlEncoded.get("major") mkString ""
    var student =  new Student(fullName = fullName, password = hashedPassword, school = school, email = email, major  = major)
    var success = student.write()
    if (success == None) {
      controllers.Status.failure
    } else {
      controllers.Status.success
    }
  }
}
