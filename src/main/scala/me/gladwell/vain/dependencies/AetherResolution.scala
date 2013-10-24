package me.gladwell.vain.dependencies

trait AetherResolution extends Resolution {

  override def resolveDependencies(dependencies: Traversable[Dependency]): Traversable[ResolvedDependency] = {
    println(s"[vain] resolving dependencies=$dependencies")
    Seq()
  }

}
