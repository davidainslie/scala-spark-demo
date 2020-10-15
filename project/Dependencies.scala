import sbt._

object Dependencies {
  def apply(): Seq[ModuleID] = Seq(
    scalatest, scalaMock,
    pureConfig, console4Cats, log4Cats, fansi,
    decline, betterFiles,
    monix, cats, monocle, refined, shapeless,
    spark
  ).flatten

  lazy val scalatest: Seq[ModuleID] = Seq(
    "org.scalatest" %% "scalatest" % "3.2.2" % "test, it" withSources() withJavadoc()
  )

  lazy val scalaMock: Seq[ModuleID] = Seq(
    "org.scalamock" %% "scalamock" % "5.0.0" % "test, it" withSources() withJavadoc()
  )

  lazy val pureConfig: Seq[ModuleID] = {
    val group = "com.github.pureconfig"
    val version = "0.14.0"

    Seq("pureconfig").map(group %% _ % version withSources() withJavadoc())
  }

  lazy val console4Cats: Seq[ModuleID] = Seq(
    "dev.profunktor" %% "console4cats" % "0.8.1"
  )

  lazy val log4Cats: Seq[ModuleID] = {
    val group = "io.chrisdavenport"
    val version = "1.1.1"

    Seq(
      "log4cats-core", "log4cats-slf4j"
    ).map(group %% _ % version)
  }

  lazy val fansi: Seq[ModuleID] = Seq(
    "com.lihaoyi" %% "fansi" % "0.2.7"
  )

  lazy val decline: Seq[ModuleID] = Seq(
    "com.monovore" %% "decline" % "1.3.0"
  )

  lazy val betterFiles: Seq[ModuleID] = Seq(
    "com.github.pathikrit" %% "better-files" % "3.8.0"
  )

  lazy val monix: Seq[ModuleID] = Seq(
    "io.monix" %% "monix" % "3.2.2" withSources() withJavadoc()
  )

  lazy val cats: Seq[ModuleID] = {
    val group = "org.typelevel"
    val version = "2.2.0"

    Seq(
      "cats-core", "cats-effect", "cats-free"
    ).map(group %% _ % version withSources() withJavadoc()) ++ Seq(
      "cats-laws", "cats-testkit"
    ).map(group %% _ % version % "test, it" withSources() withJavadoc())
  }

  lazy val monocle: Seq[ModuleID] = {
    val group = "com.github.julien-truffaut"
    val version = "2.1.0"

    Seq(
      "monocle-core", "monocle-macro", "monocle-generic"
    ).map(group %% _ % version withSources() withJavadoc()) ++ Seq(
      "monocle-law"
    ).map(group %% _ % version % "test, it" withSources() withJavadoc())
  }

  lazy val refined: Seq[ModuleID] = {
    val group = "eu.timepit"
    val version = "0.9.16"

    Seq(
      "refined", "refined-pureconfig", "refined-cats"
    ).map(group %% _ % version withSources() withJavadoc())
  }

  lazy val shapeless: Seq[ModuleID] = Seq(
    "com.chuusai" %% "shapeless" % "2.3.3"
  )

  lazy val spark: Seq[ModuleID] = {
    val group = "org.apache.spark"
    val version = "3.0.1"

    Seq(
      "spark-core",
      "spark-sql",
      "spark-streaming",
      "spark-mllib",
      "spark-hive",
      "spark-graphx",
      "spark-repl"
    ).map(group %% _ % version)
  }
}

/*
"org.apache.spark" %% "spark-core" % sparkVersion % "provided",
"com.amazonaws" % "aws-java-sdk-s3" % awsSDKVersion % "provided"
 */