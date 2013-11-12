package me.gladwell.vain

import me.gladwell.vain.compile.ScalaCompiler

class App extends xsbti.AppMain {

  def run(config: xsbti.AppConfiguration) = {
    Exit(App.run(config.arguments))
  }

}

object App extends BuildApp with ScalaCompiler {

  def main(args: Array[String]) {
    System.exit(run(args))
  }

}

case class Exit(val code: Int) extends xsbti.Exit
