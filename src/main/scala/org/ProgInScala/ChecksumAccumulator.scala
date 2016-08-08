package org.ProgInScala

/**
  * Created by rahul on 22/2/16.
  */

import scala.collection.mutable.Map
import ChecksumAccumulator.calculate

class ChecksumAccumulator {
  private var sum = 0

  def add(b: Byte) { sum += b }

  def checksum(): Int = ~ (sum & 0xFF) + 1

}

object ChecksumAccumulator {

  private val cache = Map[String, Int]()
  val awss = scala.collection.mutable.WeakHashMap

  def calculate(s: String) = {

    if(cache.contains(s))
      cache(s)
    else {
      val acc = new ChecksumAccumulator
      for(c <- s) acc.add(c.toByte)
      val cs = acc.checksum()
      cache += (s -> cs)
      val amandkj: Symbol = 'knsfkn
      cs

    }
  }
}


object Summer extends App {
   for(season <- List("fall", "winter", "spring"))
      println(season +   " : " + calculate(season))

}
