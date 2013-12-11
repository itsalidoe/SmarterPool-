package controllers

import play.api.mvc._
import controllers.bootstrap.{Features, Marketing, Carousel, Featurette}
import play.api.libs.json.Json
import scala.collection.immutable.HashMap
import utilities.ContentReader

abstract class MarketingPage extends Controller {

  implicit val format = Json.format[Featurette]

  val pathToUrl = HashMap[String, String](
    "/faq" -> routes.General.faq.toString()
  )

  private def features(file: String): List[Featurette] = {
    ContentReader.ReadJson[List[Featurette]](file, List.empty) map { feature =>
      if (feature.imageLink != "") {
        feature.copy(imageLink = routes.Assets.at(feature.imageLink).toString())
      } else {
        feature
      }
    } map { feature =>
      feature.copy(buttonLink = pathToUrl.getOrElse(feature.buttonLink, feature.buttonLink))
    }
  }

  def Carousel(file: String): Carousel = {
    new Carousel(features(file))
  }

  def Marketing(file: String, minimum: Int = 4): Marketing = {
    new Marketing(features(file), minimum)
  }

  def LargeMarketing(file: String): Features = {
    new Features(features(file))
  }
}
