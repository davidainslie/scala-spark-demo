package com.backwards.text

import scala.Console._

object Text {
  val red: String => String =
    RED + _ + RESET

  val green: String => String =
    GREEN + _ + RESET
}