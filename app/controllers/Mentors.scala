package controllers

import play.api.mvc._
import controllers.bootstrap.{Features, Marketing, Carousel, Featurette}
import models.Mentor

/**
 * Created with IntelliJ IDEA.
 * User: sean
 * Date: 17/11/13
 * Time: 7:32 PM
 * To change this template use File | Settings | File Templates.
 */
object Mentors extends Controller {

  def index = Action {
    val carouselFeatures: List[Featurette] = List(
      new Featurette(
        "Mentor Students",
        """SmarterPool allows you to be a mentor for students in university and looking to start their careers.""".stripMargin,
        "Sign up today",
        imageLink = routes.Assets.at("images/slide-01.jpg").toString(),
        buttonLink = "#signup",
        classes = "active"
      ),
      new Featurette(
        "Earn Rewards",
        """Mentors will be rewarded for their efforts through our rewards and recognition system.""".stripMargin,
        "Sign up today",
        imageLink = routes.Assets.at("images/slide-02.jpg").toString(),
        buttonLink = "#signup"
      )
    )
    val carousel = new Carousel(carouselFeatures)

    val marketingFeatures: List[Featurette] = List(
      new Featurette(
        "What is SmarterPool?",
        """SmarterPool is a an online platform that connects students with companies through real world challenges. This
          |solution was created as a result of the high student/graduate unemployment and lack of corporate innovation
          |happening in India. By posting challenges, companies identify students based on skills, and use a larger,
          |smarter pool, to collaborate on innovative solutions. Students find challenges that resonate with their
          |interests and studies, and earn temporary income for their work.
        """.stripMargin
      ),
      new Featurette(
        "Why Mentor?",
        """The next generation of students need your help to develop the skills and the career to succeed. Help as much
          |or as little as you want as we provide you an easy platform to do it. We also encourage mentorship by offering
          |rewards and recognition to those that mentor successfully.
        """.stripMargin
      ),
      new Featurette(
        "",
        "",
        button = "See Our FAQ",
        buttonLink = routes.Companies.faq.toString()
      )
    )
    val marketing = new Marketing(marketingFeatures, 2)

    val features: List[Featurette] = List(
      new Featurette(
        "Oh yeah, it's that good.",
        "Sign up to view the complete list of companies that we are partnered with.",
        tagline = "See for yourself."
      )
    )
    val feature = new Features(features)
    Ok(views.html.base("Mentors.")(carousel.Html)(marketing.Html + feature.Html + views.html.signup.mentor_signup.apply())("mentors"))
  }

  def signUp = Action { request =>
    val email = request.body.asFormUrlEncoded.get("email") mkString ""
    var mentor =  new Mentor(email = email)
    var success = mentor.write()
    if (success == None) {
      controllers.Status.failure
    } else {
      controllers.Status.success
    }
  }
}
