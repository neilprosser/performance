import sbt._
import de.element34.sbteclipsify._
import Process._

class Project(info: ProjectInfo) extends DefaultWebProject(info) with AkkaProject with Eclipsify with IdeaProject {
  
  // -------------------------------------------------------------------------------------------------------------------
  // All repositories *must* go here! See ModuleConfigurations below.
  // -------------------------------------------------------------------------------------------------------------------
  object Repositories {
    // e.g. val akkaRepo = MavenRepository("Akka Repository", "http://akka.io/repository")
  }

  override def compileOptions: Seq[CompileOption] = Deprecation :: Nil
  
  // -------------------------------------------------------------------------------------------------------------------
  // ModuleConfigurations
  // Every dependency that cannot be resolved from the built-in repositories (Maven Central and Scala Tools Releases)
  // must be resolved from a ModuleConfiguration. This will result in a significant acceleration of the update action.
  // Therefore, if repositories are defined, this must happen as def, not as val.
  // -------------------------------------------------------------------------------------------------------------------
  import Repositories._
  // e.g. val akkaModuleConfig = ModuleConfiguration("se.scalablesolutions.akka", akkaRepo)

  // -------------------------------------------------------------------------------------------------------------------
  // Dependencies
  // -------------------------------------------------------------------------------------------------------------------
  override val akkaActor  = akkaModule("actor") withSources() // it's good to always have the sources around
  val akkaHttp            = akkaModule("http")  withSources()
  val spray               = "cc.spray" %% "spray" % "0.5.0" % "compile" withSources()
  val liftJson            = "net.liftweb" %% "lift-json" % "2.3" % "compile" withSources()
  val casbah              = "com.mongodb.casbah" %% "casbah" % "2.1.2" % "compile"

  val JETTY_VERSION = "8.0.0.M2"
  val specs       = "org.scala-tools.testing" %% "specs" % "1.6.7" % "test"
  val jettyServer = "org.eclipse.jetty" % "jetty-server" % JETTY_VERSION % "test"
  val jettyWebApp = "org.eclipse.jetty" % "jetty-webapp" % JETTY_VERSION % "test"
}
