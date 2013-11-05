import me.gladwell.vain.ScalaModule
import me.gladwell.vain.Dep

package object demo extends ScalaModule {

  dependsOn(Seq(Dep(group = "commons-lang", name = "commons-lang", version = "2.4")))

}
