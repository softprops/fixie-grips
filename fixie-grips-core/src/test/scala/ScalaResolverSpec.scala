package fixiegrips

import com.github.jknack.handlebars.{
  Context, Handlebars, Template => HandlebarsTemplate, ValueResolver
}
import com.github.jknack.handlebars.io.FileTemplateLoader
import org.scalatest.FunSpec

class ScalaResolverSpec extends FunSpec {
  val handlebars = new Handlebars(new FileTemplateLoader(""))
  def newContext(obj: Object) =
    Context.newBuilder(obj).resolver(ScalaResolver).build()

  describe("ScalaResolver") {
    it ("should resolve") {
      val template = handlebars.compileInline("Hello {{foo}}.")
      assert(template(newContext(Map("foo" -> "bar"))) === "Hello bar.")
    }
  }
}
