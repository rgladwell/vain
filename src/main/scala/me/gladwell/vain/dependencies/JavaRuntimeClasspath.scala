package me.gladwell.vain.dependencies

import me.gladwell.vain.Logging
import java.io.File
import java.net.URLClassLoader
import scala.reflect.macros.Context

trait JavaRuntimeClasspath extends Classpath with Logging {

  def addToClasspath(dependencies: Traversable[File]) = {
    log(s"adding $dependencies to java runtime classpath")
    val currentThreadClassLoader = Thread.currentThread().getContextClassLoader();
    val urlClassLoader = new URLClassLoader(dependencies.map(_.toURI.toURL).toArray, currentThreadClassLoader);
    Thread.currentThread().setContextClassLoader(urlClassLoader);
  }

}
