package me.gladwell.vain.dependencies

import java.io.File
import me.gladwell.vain.Dep

trait Resolution {

  def resolveDependencies(dependencies: Seq[Dep]): Traversable[File]

}
