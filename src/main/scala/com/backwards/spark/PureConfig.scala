package com.backwards.spark

import scala.language.experimental.macros
import pureconfig._
import pureconfig.generic.ExportMacros

/**
 * Copy and paste from PureConfig - Silly Intellij can optimise imports and remove this dependency when it is actually used.
 * An object that, when imported, provides implicit `ConfigReader` and `ConfigWriter` instances for value classes, tuples, case classes and sealed traits.
 */
object PureConfig extends PureConfig

trait PureConfig {
  implicit def exportReader[A]: Exported[ConfigReader[A]] = macro ExportMacros.exportDerivedReader[A]
  implicit def exportWriter[A]: Exported[ConfigWriter[A]] = macro ExportMacros.exportDerivedWriter[A]
}