package models

import java.lang.reflect.Field

/**
 * Created with IntelliJ IDEA.
 * User: sean
 * Date: 20/11/13
 * Time: 10:23 PM
 * To change this template use File | Settings | File Templates.
 */
class Mentor(
               var id: Int = 0,
               var timestamp: Int = 0,
               var username: String = "",
               var password: String = "",
               var email: String = "",
               var field: String = "",
               var linkedinId: String = "")
  extends Model[Mentor] {
  def tableName = "Mentor"
  def instantiate = new Mentor()
  def getFields: Array[Field] = classOf[Mentor].getDeclaredFields
}
