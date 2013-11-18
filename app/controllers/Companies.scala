package controllers

import play.api.mvc._
import controllers.bootstrap.{Features, Marketing, Carousel, Featurette}

object Companies extends Controller {
  
  def index = Action {
    val carouselFeatures: List[Featurette] = List(
      new Featurette(
        "Hire Skilled Students.",
        "With our platform that allows you to post skill testing challenges, find new members of your team who will make a big difference.",
        "Learn More",
        imageLink = routes.Assets.at("images/slide-01.jpg").toString(),
        classes = "active"
      ),
      new Featurette(
        "We have over 25 schools and 1000 students.",
        "With over 25 schools and 1000 different students, find the perfect one to join your team in no time!",
        "Learn More",
        imageLink = routes.Assets.at("images/slide-02.jpg").toString()
      ),
      new Featurette(
        "Convinced?",
        "Join the growing list of companies who is using SmarterPool for smarter hiring.",
        "Sign up Today",
        imageLink = routes.Assets.at("images/slide-03.jpg").toString()
      )
    )
    val carousel = new Carousel(carouselFeatures)

    val marketingFeatures: List[Featurette] = List(
      new Featurette(
        "Consolidate.",
        "SmarterPool allows you to upload challenges, and allows students to do the same with their results, all in one platform."
      ),
      new Featurette(
        "Focus on the talent.",
        "Choose from a list of schools with robust filtering to get the intern that you really want."
      ),
      new Featurette(
        "Target and Acquire.",
        "Gather data on people who are interested in working for your company, and send directed challenges."
      )
    )
    val marketing = new Marketing(marketingFeatures)

    val features: List[Featurette] = List(
      new Featurette(
        "Oh yeah, it's that good.",
        "Sign up to talk to an agent about how SmarterPool can help you hire from a smarter pool of candidates.",
        "Sign up Today",
        tagline = "See for yourself."
      )
    )
    val feature = new Features(features)
    Ok(views.html.base("Companies.")(carousel.Html)(marketing.Html)(feature.Html))
  }

}