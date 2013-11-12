package me.gladwell.vain

import me.gladwell.vain.compile.Compiler

trait BuildApp extends Logging {
  this: Compiler =>

  def run(args: Array[String]): Int = {
    log("executing vain with arguments=" + args.mkString(" "))
    val path = args.head
    compile(path)
    0
  }

}
