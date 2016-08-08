package org.ProgInScala.ch7

/**
  * Created by rahul on 24/2/16.
  */
object Util extends App {

  val filesHere = new java.io.File(".").listFiles()


  def fileLines(file: java.io.File) =
    scala.io.Source.fromFile(file).getLines().toList

  def grep(pattern: String) =
  for(
    file <- filesHere
    if file.isFile;
    line <- fileLines(file);
    trimmed = line.trim
    if line.trim.matches(pattern)
  ) println(file +": " + line.trim)

  grep("thisisit")

}
