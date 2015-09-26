package me.gladwell.vain.compile

import java.io.File
import me.gladwell.vain.Logging
import java.net.URLClassLoader
import me.gladwell.vain.DefaultConfiguration
import me.gladwell.vain.Configuration

trait ScalaCompiler extends Compiler {
  this: Configuration with Logging =>

  private def recursiveListFiles(f: File): Array[File] = {
    val these = f.listFiles
    these ++ these.filter(_.isDirectory).flatMap(recursiveListFiles)
  }

  def compile(path: String) = {
    val files = recursiveListFiles(new File(path)).map(_.getAbsolutePath()).toList.filter(_.endsWith(".scala"));
    log(s"compiling files=$files")

    val cl = Thread.currentThread.getContextClassLoader
    val vainClasspath = (new File(compileClasspath)).getAbsoluteFile
    if(!vainClasspath.exists) vainClasspath.mkdirs()
    val classpath = (cl.asInstanceOf[URLClassLoader]).getURLs ++ Seq(vainClasspath.toURI.toURL)
    val process = Seq("scalac", "-classpath", classpath.map(_.getFile).mkString(File.pathSeparator)) ++ files

    println(process)
    import scala.sys.process._
    process.!
  }

}
