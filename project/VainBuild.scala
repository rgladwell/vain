import sbt._
import sbt.Keys._

object VainBuild extends Build {

  lazy val vain = Project(
    id = "vain",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "vain",
      organization := "me.gladwell",
      version := "1.0.0-SNAPSHOT",
      scalaVersion := "2.10.2",
      libraryDependencies ++= Seq(
        "org.scala-lang" % "scala-compiler" % scalaVersion.value,
        "org.scala-lang" % "scala-reflect" % scalaVersion.value,
        "org.scalatest" %% "scalatest" % "2.0.RC2" % "test"
      )
    )
  )
}
