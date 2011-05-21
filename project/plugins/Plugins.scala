import sbt._

class Plugins(info: ProjectInfo) extends PluginDefinition(info) {

    object Repositories {
        val akkaRepo = MavenRepository("Akka Repository", "http://akka.io/repository")
    }

    import Repositories._
    lazy val akkaModuleConfig = ModuleConfiguration("se.scalablesolutions.akka", akkaRepo)

    val akkaPlugin = "se.scalablesolutions.akka" % "akka-sbt-plugin" % "1.0"
    val eclipse = "de.element34" % "sbt-eclipsify" % "0.7.0"
    val sbtIdeaRepo = "sbt-idea-repo" at "http://mpeltonen.github.com/maven/"
    val sbtIdea = "com.github.mpeltonen" % "sbt-idea-plugin" % "0.4.0"

}
