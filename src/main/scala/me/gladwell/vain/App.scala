package me.gladwell.vain

import me.gladwell.vain.compile.ScalaCompiler

class App extends xsbti.AppMain {

  def run(config: xsbti.AppConfiguration) = {
    Exit(App.run(config.arguments))
  }

}

object App extends Logging with ScalaCompiler {

  def run(args: Array[String]): Int = {
    log("executing vain with arguments=" + args.mkString(" "))
    val path = args.head
    compile(path)
    0
  }

  def main(args: Array[String]) {
    System.exit(run(args))
  }

}

case class Exit(val code: Int) extends xsbti.Exit
