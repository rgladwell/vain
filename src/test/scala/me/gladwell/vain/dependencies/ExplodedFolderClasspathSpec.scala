package me.gladwell.vain.dependencies

import java.io.File
import me.gladwell.vain.Configuration
import me.gladwell.vain.Specification

class ExplodedFolderClasspathSpec extends Specification {

  trait TestConfiguration extends Configuration {
    val compileClasspath = "target/test/classes"
    val localRepoPath = ""
  }

  "ExplodedFolderClasspath" should "explode Java archives to configured classpath folder" in {
    Given("a test classpath folder")
    object classpath extends ExplodedFolderClasspath with TestConfiguration

    When("an archive is added to the classpath")
    classpath.addToClasspath(Seq(new File("src/test/scala/me/gladwell/vain/dependencies/archive.zip")))

    Then("the archive should be unzipped into the classpath folder")
    assert(new File(classpath.compileClasspath, "test.txt").exists)
  }

}
