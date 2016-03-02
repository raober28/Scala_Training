package org.ProgInScala.ch9

import java.io.{PrintWriter, File}
import java.util.Date

/**
  * Created by rahul on 29/2/16.
  */
object FileMatcher {

  private def filesHere = new java.io.File(".").listFiles()

  def filesEnding(query: String) = filesMatching(_.endsWith(query))

  def filesContaining(query: String) = filesMatching(_.contains(query))

  def fileRegex(query : String) = filesMatching(_.matches(query))

  def filesMatching(matcher: (String) => Boolean) = {
    for(file <- filesHere if matcher(file.getName)) yield file

  }


  def withPrintWriter(file: File)(op: PrintWriter => Unit) = {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
      writer.close()
    }
  }

  val file = new File("date.txt")
  withPrintWriter(file) {
    writer => writer.println(new Date())
  }

}
