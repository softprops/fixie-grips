package fixiegrips

import com.github.jknack.handlebars.{ Context, Handlebars }
import org.scalatest.FunSpec

class ScalaResolverSpec extends FunSpec {
  val handlebars = new Handlebars()
  def newContext(obj: Object) =
    Context.newBuilder(obj).resolver(ScalaResolver).build()

  describe("ScalaResolver") {
    it ("should resolve") {
      val template = handlebars.compileInline("Hello {{foo}}.")
      assert(template(newContext(Map("foo" -> "bar"))) === "Hello bar.")
    }
  }
}
