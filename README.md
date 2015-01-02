# fixie-grips

> equips your [handlebars](https://github.com/jknack/handlebars.java) templates with scala hand grips

# usage

[Handlebars](http://handlebarsjs.com/), like its cousin, [Mustache](http://mustache.github.io/) is a minimal templating language. This
library adds Scala extensions to [handlebars.java](https://github.com/jknack/handlebars.java), a well maintained handlebars implementation
of handlebars on the JVM.

Handlebars separates template content from data. A template processor will need to be equipped with a way to _resolve_ pieces of data by name.
Templates define references to names that must be resolvable in a given scope. See [this article](http://jknack.github.io/handlebars.java/stack.html) on context stacks for more information.

Let's walk through some short examples.

Create a new handlebars template processor and a simple template, specified as an inline string.

```scala
import com.github.jknack.handlebars.Handlebars

val handlebars = new HandleBars()
val template = handlebars.compileInline("Hello {{foo}}.")
```

Handlebars.java provides a flexible interface for configuring contexts and value resolvers. Below is an example
of defining a context builder that adds a `ScalaResolver`.

```scala
import com.github.jknack.handlebars.Context
import fixiegrips.ScalaResolver

def ctx(obj: Object) =
  Context.newBuilder(obj).resolver(ScalaResolver).build
  
template(ctx(Map("foo" -> "bar"))) // Hello bar.
```

Besides resolving values handlebars also defines a pluggable system for defining helper directives.

Some of the default helpers are `if` and `each` with provide conditional and iterative rendering behavior, but they lack the knowledge of scala 
collection types.

To make handlebars.java aware of things that can be iterated over in Scala, plug in `fixiegrips.ScalaHelpers` to the handlebars `registerHelpers` interface.

```scala
import com.github.jknack.handlebars.{ Context, Handlebars }
import fixiegrips.{ ScalaHelpers, ScalaResolver }

val handlebars = new Handlebars().registerHelpers(ScalaHelpers)
def ctx(obj: Object) =
  Context.newBuilder(obj).resolver(ScalaResolver).build
val template = handlebars.compileInline("{{#each foo}}{{.}} {{/each}}")
template(ctx(Map("foo" -> Seq("a", "b")))) // a b
```

### json4s


This library provides a module for making handlebars.java also intelligent about json4s, a defacto standard for JSON processing in Scala, types.

```scala
import com.github.jknack.handlebars.{ Context, Handlebars }
import org.json4s.JsonAST._
import org.json4s.JsonDSL._

val handlebars = new Handlebars()
def ctx(obj: Object) =
  Context.newBuilder(obj).resolver(Json4sResolver).build

val json = ("foo" -> "bar") ~ ("baz" -> "boom")
val template = handlebars.compileInline("Hello {{foo}}.")
template(ctx(json)) // Hello bar.
```

Doug Tangren (softprops) 2014
