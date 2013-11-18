package controllers.bootstrap

import play.api.templates.Html

/**
 * Created with IntelliJ IDEA.
 * User: sean
 * Date: 17/11/13
 * Time: 5:10 PM
 * To change this template use File | Settings | File Templates.
 */
class Carousel(val featurettes: List[Featurette]) {
  def Html: Html = {
    views.html.carousel(featurettes)
  }
}
