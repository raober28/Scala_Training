package org.coursera

import scala.math._

/**
  * Created by rahul on 13/6/16.
  */
object Currying {

  val tolerance = 0.0001

  def isCloseEnough(x: Double, y: Double) =
    abs((x - y) / x) / x < tolerance


  def fixedPoint(f: Double => Double)(firstGuess: Double) = {
    def iterate(guess: Double): Double = {
      println("guess :" + guess)
      val next = f(guess)
      if (isCloseEnough(guess, next)) next
      else iterate(next)
    }
    iterate(firstGuess)
  }

  fixedPoint(x => 1 + x / 2)(1)

  def sqrt(x: Double) = fixedPoint(y => (y + x / y) / 2)(1)

  def main (args: Array[String]) {
    sqrt(2)
  }


  def product(f: Int => Int)(a: Int, b: Int): Int = {
    if(a > b) 1
    else  f(a) * product(f)(a + 1, b)
  }




}
