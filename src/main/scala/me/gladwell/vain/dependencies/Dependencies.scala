package me.gladwell.vain.dependencies

import scala.reflect.macros.Context
import java.net.URLClassLoader
import me.gladwell.vain.Logging
import me.gladwell.vain.Configuration
import java.io.File
import me.gladwell.vain.Dep

private[dependencies] trait Dependencies extends Logging {
  this: Resolution with Classpath with Configuration =>

  def manageDependencies(c: Context)(dependencies: c.Expr[Traversable[Dep]]): c.Expr[Unit] = {
    log("initialising")
    import c.universe._

    println(showRaw(dependencies))

    def parseDependency(tree: Tree): Dep = {
      tree match {
        case Apply(_, List(Literal(Constant(group: String)), Literal(Constant(name: String)), Literal(Constant(version: String)))) => {
          Dep(group, name, version)
        }
      }
    }

    val compileDeps = dependencies match {
      case c.Expr(Apply(_, args)) => args.map{ parseDependency(_) }
    }

    val st = c.universe.asInstanceOf[scala.reflect.internal.SymbolTable]
    if (st.isCompilerUniverse) {
      val global = st.asInstanceOf[scala.tools.nsc.Global]
      
      val classpath = global.classPath.asURLs.find(url => url.toExternalForm.contains(compileClasspath)) getOrElse {
        c.abort(c.enclosingPosition, s"could not find '$compileClasspath' on compile classpath=${global.classPath}")
      }

      addToClasspath(resolveDependencies(compileDeps))
      println(s"classpath=$classpath")
      val (updated, _) = global invalidateClassPathEntries classpath.toExternalForm
      c.info(c.enclosingPosition, s"Updated symbols $updated", true)
    }

    c.Expr[Unit](
      Apply(
        Ident(newTermName("addToClasspath")),
        List(Apply(
          Ident(newTermName("resolveDependencies")),
          List(dependencies.tree))
        ))
      )
  }

}
