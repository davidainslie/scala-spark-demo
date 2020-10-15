package com.backwards.spark

import cats.implicits._
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import com.backwards.spark.Odd._

class OddSpec extends AnyWordSpec with Matchers {
  "Odd" should {
    "group by key" in {
      val xs = List(1 -> 2, 1 -> 2, 1 -> 2)

      groupByKey(xs) mustBe Map(1 -> xs)
    }

    "tag values (with a one)" in {
      val xs = List(1 -> 2, 1 -> 2, 1 -> 2)

      tagValues(xs) mustBe List(2 -> 1, 2 -> 1, 2 -> 1)
    }

    "sum values" in {
      val xs = List(1 -> 2, 1 -> 2, 1 -> 2)

      sumValues(groupByKey(xs)) mustBe Map(1 -> 6)
    }

    "sum tagged values" in {
      val xs = List(1 -> 2, 1 -> 2, 1 -> 2)

      sumValues(groupByKey(tagValues(xs))) mustBe Map(2 -> 3)
    }

    "filter odd instances" in {
      val twos = List(1 -> 2, 1 -> 2, 1 -> 2)
      val nines = List(5 -> 9, 5 -> 9)

      val xs = twos ++ nines

      filterOddInstances(sumValues(groupByKey(tagValues(xs)))) mustBe Map(2 -> 3)
    }
  }

  "List of int pairs" should {
    "have one value occurring an odd number of times for a given key" in {
      val xs = List(1 -> 2, 1 -> 2, 1 -> 2)

      oddInstances(xs) mustBe Map(1 -> 2.some)
    }

    "have only one value occurring an odd number of times for a given key" in {
      val xs = List(
        1 -> 2, 1 -> 2, 1 -> 2,
        1 -> 99, 1 -> 99
      )

      oddInstances(xs) mustBe Map(1 -> 2.some)
    }
  }
}