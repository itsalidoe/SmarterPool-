package models

import java.lang.reflect.Field

/**
 * Created with IntelliJ IDEA.
 * User: sean
 * Date: 21/11/13
 * Time: 1:03 AM
 * To change this template use File | Settings | File Templates.
 */
trait ModelUtilities {
  protected def isNumeric(field: Field) = {
    List(
      "double",
      "int"
    ).contains(field.getType.getName)
  }

  protected def convertValue(field: Field, value: Any): String = {
    (if (isNumeric(field)) "%s" else "'%s'").format(value.toString)
  }
}
