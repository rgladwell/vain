package me.gladwell.vain.dependencies

import scala.reflect.macros.Context
import java.net.URLClassLoader
import me.gladwell.vain.Logging

private[dependencies] trait Dependencies extends Logging {
  this: Resolution =>

  def manageDependencies(c: Context)(dependencies: c.Expr[Traversable[Dependency]]): c.Expr[Unit] = {
    log("initialising")
    import c.universe._

    c.Expr[Unit](
      Apply(
        Ident(newTermName("addToClasspath")),
        List(Apply(
          Ident(newTermName("resolveDependencies")),
          List(dependencies.tree)
        ))
      )
    )
  }

}
