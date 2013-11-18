package controllers.bootstrap

import play.api.templates.Html

/**
 * Created with IntelliJ IDEA.
 * User: sean
 * Date: 17/11/13
 * Time: 6:18 PM
 * To change this template use File | Settings | File Templates.
 */
class Marketing (val featurettes: List[Featurette]) {
  def Html: Html = {
    val spanClass = "span" + (12/featurettes.count(_ => true).min(4)).toString
    views.html.marketing(featurettes)(spanClass)
  }
}
