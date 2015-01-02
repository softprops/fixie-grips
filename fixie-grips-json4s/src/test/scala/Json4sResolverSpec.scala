package fixiegrips

import com.github.jknack.handlebars.{ Context, Handlebars }
import org.scalatest.FunSpec
import org.json4s.JsonAST._
import org.json4s.JsonDSL._

class Json4sResolverSpec extends FunSpec {
  val handlebars = new Handlebars()
  def newContext(obj: Object) =
    Context.newBuilder(obj).resolver(Json4sResolver).build()

  describe("Json4sResolver") {
    it ("should resolve") {
      val template = handlebars.compileInline("Hello {{foo}}.")
      assert(template(newContext(("foo" -> "bar"): JValue)) === "Hello bar.")
    }
  }
}

