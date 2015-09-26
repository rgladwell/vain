package me.gladwell.vain.dependencies

import java.io.File
import java.net.URLClassLoader
import me.gladwell.vain.{ConsoleLogging, Specification}

class JavaRuntimeClasspathSpec extends Specification {

  "ExplodedFolderClasspath" should "explode Java archives to configured classpath folder" in {
    Given("a test runtime classpath")
    object classpath extends JavaRuntimeClasspath with ConsoleLogging

    When("an archive is added to the classpath")
    val file = new File("src/test/scala/me/gladwell/vain/dependencies/archive.zip")
    classpath.addToClasspath(Seq(file))

    Then("the archive URL should be added to the Java class loader")
    val classLoader = Thread.currentThread().getContextClassLoader().asInstanceOf[URLClassLoader]
    assert(classLoader.getURLs().contains(file.toURI.toURL))
  }

}
