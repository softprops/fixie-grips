package fixiegrips

import com.github.jknack.handlebars.{ Context, Options }
import com.github.jknack.handlebars.helper.{ EachHelper, IfHelper }
import scala.collection.Iterable

/** scala specific handlebar helpers */
trait ScalaHelpers {  
  
  /** overriding default `each` helper to support scala iterable things */
  def each(obj: Object, options: Options): CharSequence =
    obj match {
      case  it: Iterable[_] =>
        eachIterable(it, options)
      case e =>
        EachHelper.INSTANCE(obj, options)
    }

  /** overriding default `if` helper to support scala falsy things */
  def `if`(obj: Object, options: Options): CharSequence =
    obj match {
      case it: Iterable[_] =>
        if (it.isEmpty) options.inverse() else options.fn()
      case _ =>
        IfHelper.INSTANCE(obj, options)
    }

  def eachNamed(
    named: Iterable[(String, _)], options: Options): CharSequence = {
    val sb = new StringBuilder()
    if (named.isEmpty) sb.append(options.inverse()) else {
      val parent = options.context
      for ((key, value) <- named) {
        val ctx = Context.newBuilder(parent, value)
          .combine("@key", key)
          .build()
        sb.append(options(options.fn, ctx))
        ctx.destroy()
      }
    }
    sb
  }

  protected def eachIterable(
    it: Iterable[_], options: Options): CharSequence = {
    val sb = new StringBuilder()
    if (it.isEmpty) sb.append(options.inverse()) else {
      val parent = options.context
      def append(i: Int, iter: Iterator[_]): Unit =
        if (iter.hasNext) {
          val even = i % 2 == 0
          val ctx = Context.newBuilder(parent, iter.next)
            .combine("@index", i)
            .combine("@first", if (i == 0) "first" else "")
            .combine("@last", if (!iter.hasNext) "last" else "")
            .combine("@odd", if (!even) "odd" else "")
            .combine("@even", if (even) "even" else "")
            .build()
          sb.append(options(options.fn, ctx))
          ctx.destroy()
          append(i + 1, iter)
        }
      append(0, it.iterator)
    }
    sb
  }

  /** shim to avoid https://github.com/jknack/handlebars.java/blob/master/handlebars/src/main/java/com/github/jknack/handlebars/helper/DefaultHelperRegistry.java#L211-L212. this helper isn't really helpful but it works */
  def <^> = "<^>"
}

object ScalaHelpers extends ScalaHelpers
