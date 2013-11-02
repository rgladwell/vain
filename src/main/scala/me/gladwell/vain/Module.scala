package me.gladwell.vain

import scala.language.experimental.macros

import me.gladwell.vain.dependencies._

trait Module {
  this: Resolution with Classpath =>

  def dependsOn(dependencies: Traversable[Dependency]) = macro manageDependencies

}
