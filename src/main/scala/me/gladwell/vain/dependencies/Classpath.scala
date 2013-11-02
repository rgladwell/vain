package me.gladwell.vain.dependencies

import java.io.File

trait Classpath {

  def addToClasspath(dependencies: Traversable[File])

}
