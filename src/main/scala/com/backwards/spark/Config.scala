package com.backwards.spark

final case class Config(master: Option[String], fileSystemPrefix: FileSystemPrefix = FileSystemPrefix(""))

/** E.g. spark://master:7077 or local[*] */
final case class MasterUrl(value: String) extends AnyVal

/** E.g. s3:// or hdfs:// */
final case class FileSystemPrefix(path: String) extends AnyVal