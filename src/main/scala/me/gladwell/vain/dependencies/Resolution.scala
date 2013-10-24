package me.gladwell.vain.dependencies

trait Resolution {

  class ResolvedDependency

  def resolveDependencies(dependencies: Traversable[Dependency]): Traversable[ResolvedDependency]

}
