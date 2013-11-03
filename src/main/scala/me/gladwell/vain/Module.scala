package me.gladwell.vain

import scala.language.experimental.macros
import me.gladwell.vain.dependencies._
import me.gladwell.vain.Dep

trait Module {
  this: Resolution with Classpath =>

  def dependsOn(dependencies: Traversable[Dep]) = macro manageDependencies

}
