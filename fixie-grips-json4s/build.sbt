description := "handlebars.java bindings for json4s"

libraryDependencies += "org.json4s" %% "json4s-ast" % "3.2.11"

bintraySettings

bintray.Keys.packageLabels in bintray.Keys.bintray := Seq("handlebars", "templating", "json", "json4s")
