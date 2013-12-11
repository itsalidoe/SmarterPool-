package controllers.bootstrap

/**
 * Created with IntelliJ IDEA.
 * User: sean
 * Date: 17/11/13
 * Time: 5:11 PM
 * To change this template use File | Settings | File Templates.
 */
case class Featurette(
  title: String,
  lead: String,
  button: String = "",
  buttonLink: String = "#",
  tagline: String = "",
  imageLink: String = "",
  imagePull: String = "left",
  classes: String = "",
  id: String = "")
