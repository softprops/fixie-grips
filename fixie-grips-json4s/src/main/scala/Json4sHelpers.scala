package fixiegrips

import com.github.jknack.handlebars.{ Context, Options }
import com.github.jknack.handlebars.helper.{ EachHelper, IfHelper }
import org.json4s.JsonAST.{ JArray, JObject }

/** json4s specific handlebar helpers */
trait Json4sHelpers extends ScalaHelpers {
  override def each(obj: Object, options: Options): CharSequence =
    obj match {
      case ary: JArray =>
        eachIterable(ary.arr, options)
      case JObject(fields) =>
        eachNamed(fields, options)
      case _ =>
        super.each(obj, options)
    }

  /** overriding default `if` helper to support scala falsy things */
  override def `if`(obj: Object, options: Options): CharSequence =
    obj match {
      case JArray(ary) =>
        `if`(ary, options)
      case _ =>
        super.`if`(obj, options)
    }
}

object Json4sHelpers extends Json4sHelpers
