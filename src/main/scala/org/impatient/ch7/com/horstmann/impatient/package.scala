package org.impatient
package ch7.com.horstmann.impatient

import scala.math.pow

/**
  * Created by rahul on 10/2/16.
  */
package object impatient {

  var previous =  0
  val a = 1664525
  val b = 1013904223
  val n = 32

  def nextInt() = (previous * a + b) % pow(2, n)
  def nextDouble() : Double = nextInt()

  def setSeed(seed: Int): Unit = previous = seed

}
