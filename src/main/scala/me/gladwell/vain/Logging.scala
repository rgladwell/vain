package me.gladwell.vain

private [vain] trait Logging {

  def log(message: String) {
    println(s"[vain] $message")
  }

}
