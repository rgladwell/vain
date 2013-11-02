package me.gladwell.vain.dependencies

import java.io.File

trait Resolution {

  def resolveDependencies(dependencies: Traversable[Dependency]): Traversable[File]

}
