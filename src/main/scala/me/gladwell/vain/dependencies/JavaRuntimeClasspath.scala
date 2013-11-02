package me.gladwell.vain.dependencies

import me.gladwell.vain.Logging
import java.io.File
import java.net.URLClassLoader

trait JavaRuntimeClasspath extends RuntimeClasspath with Logging {

  def addToClasspath(dependencies: Traversable[File]) = {
    log(s"adding $dependencies to java runtime classpath")
    val currentThreadClassLoader = Thread.currentThread().getContextClassLoader();
    val urlClassLoader = new URLClassLoader(dependencies.map(_.toURI.toURL).toArray, currentThreadClassLoader);
    Thread.currentThread().setContextClassLoader(urlClassLoader);
  }

}
