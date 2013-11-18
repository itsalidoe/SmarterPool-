package controllers

import play.api.mvc._
import controllers.bootstrap.{Features, Marketing, Carousel, Featurette}

/**
 * Created with IntelliJ IDEA.
 * User: sean
 * Date: 17/11/13
 * Time: 7:32 PM
 * To change this template use File | Settings | File Templates.
 */
object Students  extends Controller {

  def index = Action {
    val carouselFeatures: List[Featurette] = List(
      new Featurette(
        "We are partnered with 500+ companies.",
        "There are over 2000 open positions and counting.",
        "Sign up today",
        imageLink = routes.Assets.at("images/slide-01.jpg").toString(),
        classes = "active"
      )
    )
    val carousel = new Carousel(carouselFeatures)

    val marketingFeatures: List[Featurette] = List(
      new Featurette(
        "Earn Prizes and Find Employment.",
        "The vast majority of the challenges posted offer prizes, and a chance for employment on a successful application."
      ),
      new Featurette(
        "Solve Real Challenges.",
        "Real employers post challenges in order to attract the best and brightest. This includes you."
      ),
      new Featurette(
        "Connect With Mentors.",
        "Have a brilliant idea for a new Startup? We are connected with a list of serial entrepreneurs who have been through the same situation."
      )
    )
    val marketing = new Marketing(marketingFeatures)

    val features: List[Featurette] = List(
      new Featurette(
        "Oh yeah, it's that good.",
        "Sign up to view the complete list of companies that we are partnered with.",
        "Sign up Today",
        tagline = "See for yourself."
      )
    )
    val feature = new Features(features)
    Ok(views.html.base("Companies.")(carousel.Html)(marketing.Html)(feature.Html))
  }

}
