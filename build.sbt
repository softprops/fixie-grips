organization in ThisBuild := "me.lessis"

version in ThisBuild := "0.1.0"

crossScalaVersions in ThisBuild := Seq("2.10.4", "2.11.4")

scalaVersion in ThisBuild := crossScalaVersions.value.last

publishArtifact := false

publish := {}

libraryDependencies in ThisBuild += "org.scalatest" %% "scalatest" % "2.2.1" % "test"

lazy val `fixie-grips` = project.in(file(".")).aggregate(`fixie-grips-core`, `fixie-grips-json4s`)

lazy val `fixie-grips-core` = project

lazy val `fixie-grips-json4s` = project.dependsOn(`fixie-grips-core`)
