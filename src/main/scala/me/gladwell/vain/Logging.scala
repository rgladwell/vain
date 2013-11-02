package me.gladwell.vain

import java.io.FileWriter

private[vain] trait Logging {

  def log(message: String) {
    println(s"[vain] $message")
    val writer = new FileWriter("output.log", true)
    try {
      writer.write(s"[vain] $message")
    } finally writer.close()
  }

}
