package org.impatient.ch9

import scala.io.Source

/**
  * Created by rahul on 16/3/16.
  */
object Reader {

  def main(args: Array[String]) {
    val source = Source.fromURL("https://www.facebook.com", "UTF-8")
    val lineIterator = source.getLines()

    val tokens = source.mkString.split("\\s+")

    tokens foreach println

  }

}
