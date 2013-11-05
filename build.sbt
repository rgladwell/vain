seq(conscriptSettings :_*)

organization := "me.gladwell"

name := "vain"

version := "1.0.0-SNAPSHOT"

libraryDependencies <++= (scalaVersion)(sv =>
  Seq(
    "org.scala-lang" % "scala-reflect" % sv,
    "org.scala-lang" % "scala-compiler" % sv
  )
)

libraryDependencies += "javax.inject" % "javax.inject" % "1"

libraryDependencies += "org.eclipse.aether" % "aether-api" % "0.9.0.M3"

libraryDependencies += "org.eclipse.aether" % "aether-spi" % "0.9.0.M3"

libraryDependencies += "org.eclipse.aether" % "aether-util" % "0.9.0.M3"

libraryDependencies += "org.eclipse.aether" % "aether-impl" % "0.9.0.M3"

libraryDependencies += "org.eclipse.aether" % "aether-connector-basic" % "0.9.0.M3"

libraryDependencies += "org.eclipse.aether" % "aether-transport-file" % "0.9.0.M3"

libraryDependencies += "org.eclipse.aether" % "aether-transport-http" % "0.9.0.M3"

libraryDependencies += "org.apache.maven" % "maven-aether-provider" % "3.1.1"
