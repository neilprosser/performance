import sbt._
import de.element34.sbteclipsify._
import Process._

class Project(info: ProjectInfo) extends DefaultWebProject(info) with AkkaProject with Eclipsify with IdeaProject {

    object Repositories {
    }

    override def compileOptions: Seq[CompileOption] = Deprecation :: Nil

    import Repositories._

    override val akkaActor = akkaModule("actor") withSources ()
    val akkaHttp = akkaModule("http") withSources ()
    val spray = "cc.spray" %% "spray" % "0.5.0" % "compile" withSources ()
    val liftJson = "net.liftweb" %% "lift-json" % "2.3" % "compile" withSources ()

    val JETTY_VERSION = "8.0.0.M2"
    val specs = "org.scala-tools.testing" %% "specs" % "1.6.7" % "test"
    val jettyServer = "org.eclipse.jetty" % "jetty-server" % JETTY_VERSION % "test"
    val jettyWebApp = "org.eclipse.jetty" % "jetty-webapp" % JETTY_VERSION % "test"
    
}
