package controllers.bootstrap

import play.api.templates.Html

class Carousel(val featurettes: List[Featurette]) {
  def Html: Html = {
    views.html.carousel(featurettes)
  }
}
