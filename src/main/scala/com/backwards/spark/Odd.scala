package com.backwards.spark

import cats.implicits._

object Odd {
  val groupByKey: List[(Int, Int)] => Map[Int, List[(Int, Int)]] =
    _.groupBy { case (k, _) =>
      k
    }

  val tagValues: List[(Int, Int)] => List[(Int, Int)] =
    _.map { case (_, v) =>
      v -> 1
    }

  val sumValues: Map[Int, List[(Int, Int)]] => Map[Int, Int] =
    _.collect { case (k, vs) =>
      k -> vs.map(_._2).sum
    }

  val filterOddInstances: Map[Int, Int] => Map[Int, Int] =
    _.filter { case (_, v) =>
      v % 2 != 0
    }

  def oddInstances(xs: Map[Int, Map[Int, Int]]): Map[Int, Option[Int]] =
    xs.map { case (k, taggedValues) =>
      k -> (filterOddInstances(taggedValues).toList match {
        case List((k, _)) => k.some
        case _ => none
      })
    }

  def oddInstances(xs: List[(Int, Int)]): Map[Int, Option[Int]] =
    oddInstances(groupByKey(xs).mapValues(tagValues andThen groupByKey andThen sumValues))
}