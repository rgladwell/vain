package me.gladwell.vain.dependencies

import scala.reflect.macros.Context
import java.net.URLClassLoader

trait Dependencies {
  this: Resolution =>

  def manageDependencies(c: Context)(dependencies: c.Expr[Traversable[Dependency]]): c.Expr[Unit] = {
    println("[vain] initialising")
    println("[vain] scope = compile")
    import c.universe._
    val global = c.universe.asInstanceOf[scala.tools.nsc.Global]
    println(dependencies)
//    val urlClassLoader: URLClassLoader = findURLClassLoader(global.analyzer.macroClassloader.getParent).asInstanceOf[URLClassLoader]

//    val Literal(Constant(s_dependencies: Traversable[_])) = dependencies.tree
//    resolveDependencies(s_dependencies)

    reify {
      ()
    }
  }

//  def findURLClassLoader(classLoader: ClassLoader): URLClassLoader = classLoader match {
//    case classLoader: URLClassLoader => classLoader
//    case classLoader: ClassLoader => findURLClassLoader(classLoader.getParent)
//    case _ => throw new RuntimeException("cannot find URLClassLoader")
//  }
}
