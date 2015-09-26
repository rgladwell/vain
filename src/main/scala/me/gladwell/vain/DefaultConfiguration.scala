package me.gladwell.vain

trait DefaultConfiguration extends Configuration {

  def compileClasspath = "target/classes"
  def localRepoPath = ".vain/local-repo"

}
