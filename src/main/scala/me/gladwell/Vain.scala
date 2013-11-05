package me.gladwell

class Vain extends xsbti.AppMain {
  def run(config: xsbti.AppConfiguration) = {
    Exit(Vain.run(config.arguments))
  }
}

object Vain {

  def run(args: Array[String]): Int = {
    println("Hello World: " + args.mkString(" "))
    0
  }

  def main(args: Array[String]) {
    System.exit(run(args))
  }

}

case class Exit(val code: Int) extends xsbti.Exit
