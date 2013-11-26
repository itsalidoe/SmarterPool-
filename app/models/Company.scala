package models

import java.lang.reflect.Field

/**
 * Created with IntelliJ IDEA.
 * User: sean
 * Date: 20/11/13
 * Time: 10:23 PM
 * To change this template use File | Settings | File Templates.
 */
class Company(
               var id: Int = 0,
               var timestamp: Int = 0,
               var companyName: String = "",
               var password: String = "",
               var field: String = "")
  extends Model[Company] {
  def tableName = "Company"
  def instantiate = new Company()
  def getFields: Array[Field] = classOf[Company].getDeclaredFields
}
