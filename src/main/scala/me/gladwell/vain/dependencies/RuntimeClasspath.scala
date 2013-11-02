package me.gladwell.vain.dependencies

import java.io.File

trait RuntimeClasspath {

  def addToClasspath(dependencies: Traversable[File])

}
