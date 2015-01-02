package fixiegrips

import com.github.jknack.handlebars.{ Context, Handlebars }
import org.scalatest.FunSpec

class ScalaHelpersSpec extends FunSpec {
  val handlebars = new Handlebars().registerHelpers(ScalaHelpers)
  def newContext(obj: Object) =
    Context.newBuilder(obj).resolver(ScalaResolver).build()

  describe("ScalaHelpers") {
    it ("should support #each") {
      val template = handlebars.compileInline("{{#each foo}}{{.}} {{/each}}")
      assert(template(newContext(Map("foo" -> Seq("a", "b")))) === "a b ")
    }
    it ("should support #if") {
      val template = handlebars.compileInline("{{#if foo}}yep{{/if}}")
      assert(template(newContext(Map("foo" -> Seq("a", "b")))) === "yep")
    }
  }
}
