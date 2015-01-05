description := "handlebars.java bindings for scala"

libraryDependencies += "com.github.jknack" % "handlebars" % "1.3.2"

bintraySettings

bintray.Keys.packageLabels in bintray.Keys.bintray := Seq("handlebars", "templating")
