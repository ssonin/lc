name := "lc"

version := "0.1"

scalaVersion := "2.13.6"
val scalaTestVersion = "3.2.9"

libraryDependencies += "org.scalatest" %% "scalatest" % scalaTestVersion % "test"
libraryDependencies += "org.scalatestplus" %% "scalacheck-1-15" % "3.2.9.0" % "test"
