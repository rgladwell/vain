package me.gladwell.vain.dependencies

import java.io.File

import me.gladwell.vain.{ConsoleLogging, DefaultConfiguration, Dep, Specification}
import org.scalatest.Args

class AetherResolutionSpec extends Specification {

  "AetherResolution" should "resolve remote dependencies" in {
    Given("a resolver")
    object resolver extends AetherResolution with DefaultConfiguration with ConsoleLogging

    When("dependency is resolved")
    resolver.resolveDependencies(Seq(Dep(group = "commons-lang", name = "commons-lang", version = "2.4")))

    Then("it is downloaded to the local repo")
    assert(new File(".vain/local-repo/commons-lang/commons-lang/2.4/commons-lang-2.4.jar").exists)
  }

}
