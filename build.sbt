import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "op.challenge",
      scalaVersion := "2.12.1"
    )),
    name := "Hello",
    libraryDependencies += scalaTest % Test
  )
