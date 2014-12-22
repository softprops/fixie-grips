organization := "me.lessis"

name := "fixie-grips"

version := "0.1.0-SNAPSHOT"

crossScalaVersions := Seq("2.10.4", "2.11.4")

scalaVersion := crossScalaVersions.value.last

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "com.github.jknack" % "handlebars" % "1.3.2", 
  "org.json4s" %% "json4s-ast" % "3.2.11")
