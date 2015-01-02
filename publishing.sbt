licenses in ThisBuild := Seq(("MIT", url(s"https://github.com/softprops/zoey/blob/${version.value}/LICENSE")))

pomExtra in ThisBuild := (
  <scm>
    <url>git@github.com:softprops/fixie-grips.git</url>
    <connection>scm:git:git@github.com:softprops/fixie-grips.git</connection>
  </scm>
  <developers>
    <developer>
      <id>softprops</id>
      <name>Doug Tangren</name>
      <url>https://github.com/softprops</url>
    </developer>
    </developers>
    <url>https://github.com/softprops/fixie-grips</url>)

bintraySettings

bintray.Keys.packageLabels in bintray.Keys.bintray := Seq("handlebars", "templating")
