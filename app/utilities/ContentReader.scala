package utilities

import play.api.Play
import play.api.Play.current
import play.api.libs.json.{Reads, Json}
import com.google.common.io.Files
import com.google.common.base.Charsets

/**
 * Created with IntelliJ IDEA.
 * User: sean
 * Date: 11/12/13
 * Time: 2:10 AM
 * To change this template use File | Settings | File Templates.
 */

object ContentReader {
  def ReadJson[T](filepath: String, default: T)(implicit fmt: Reads[T] = null): T = {
    Play.getExistingFile(filepath) map { file =>
      val jsonStr = Files.toString(file, Charsets.UTF_8).replaceAllLiterally("\n", "")
      Json.fromJson[T](
        Json.parse(jsonStr)
      )(fmt) getOrElse default
    } getOrElse default
  }
}
