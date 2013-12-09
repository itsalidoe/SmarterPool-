package models

import java.lang.reflect.Field

/**
 * Created with IntelliJ IDEA.
 * User: sean
 * Date: 20/11/13
 * Time: 10:23 PM
 * To change this template use File | Settings | File Templates.
 */
class Student(
               var id: Int = 0,
               var timestamp: Int = 0,
               var fullName: String = "",
               var password: String = "",
               var school: String = "",
               var email: String = "",
               var major: String = "",
               var linkedinId: String = "")
  extends Model[Student] {
  def tableName = "Student"
  def instantiate = new Student()
  def getFields: Array[Field] = classOf[Student].getDeclaredFields
}
