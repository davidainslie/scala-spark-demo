package com.backwards.spark

import scala.language.postfixOps
import scala.util.Try
import cats.data.EitherT
import cats.effect.IO
import cats.implicits._
import io.chrisdavenport.log4cats.slf4j.Slf4jLogger
import pureconfig.ConfigSource
import org.apache.spark.sql.{Dataset, SparkSession}
import com.backwards.spark.Odd._
import com.backwards.text.Text._

/**
 * Run this App which is configured by application.conf - Configurations can be overridden by system properties or environment variables
 * {{{
 *  sbt '; set javaOptions ++= Seq("-Dspark.master=local[*]"); runMain com.backwards.spark.OddInstancesApp'
 * }}}
 */
object OddInstancesApp extends PureConfig {
  def sparkSession(b: SparkSession.Builder, config: Config): Throwable Either SparkSession =
    Try(config.master.fold(b)(b.master).getOrCreate()).toEither

  def main(args: Array[String]): Unit = {
    val program: EitherT[IO, Throwable, Unit] =
      for {
        config <- EitherT.fromEither[IO](ConfigSource.default.at("spark").load[Config]).leftMap(help => new Exception(help.toString))
        spark  <- EitherT.fromEither[IO](sparkSession(SparkSession.builder.appName("odd-instances"), config))
        _      <- EitherT(IO(spark).bracket(s => run(s).attempt)(s => IO(s.stop())))
      } yield ()

    EitherT.liftF(Slf4jLogger.create[IO]).flatMap { logger =>
      program.biSemiflatMap(
        t => logger.error(t)(red(t.getMessage)),
        _ => logger.info(green("Success"))
      )
    }.value *> IO.unit unsafeRunSync()
  }

  def run(spark: SparkSession): IO[Unit] = IO {
    import spark.implicits._

    val dataFrame: Dataset[(Int, Int)] =
      spark.read
        .option("inferSchema", "true")
        .option("header", "true")
        .csv("data/in/input.csv")
        .as[(Int, Int)]

    val result = dataFrame.groupByKey(_._1).mapGroups { case (_, values) =>
      oddInstances(values.toList)
    }

    result.map(_.mkString).write.csv("data/out/output.csv")
  }
}