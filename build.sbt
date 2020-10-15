import sbt._

lazy val root = project("scala-spark-demo", file("."))
  .settings(description := "Scala Spark Demo")

def project(id: String, base: File): Project =
  Project(id, base)
    .enablePlugins(JavaAppPackaging)
    .configs(IntegrationTest)
    .settings(inConfig(IntegrationTest)(Defaults.testSettings))
    .settings(Defaults.itSettings)
    .settings(
      scalaVersion := BuildProperties("scala.version"),
      sbtVersion := BuildProperties("sbt.version"),
      organization := "com.backwards",
      name := id,
      autoStartServer := false,
      addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full),
      addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1"),
      libraryDependencies ++= Dependencies(),
      scalacOptions in (Compile, doc) ++= Seq(
        "-groups",
        "-implicits"
      ),
      scalacOptions ++= Seq(
        "-encoding", "utf8",
        "-deprecation",
        "-unchecked",
        "-language:implicitConversions",
        "-language:higherKinds",
        "-language:existentials",
        "-language:postfixOps",
        "-Xfatal-warnings",
        "-Ywarn-value-discard"
      ),
      fork := true
    )