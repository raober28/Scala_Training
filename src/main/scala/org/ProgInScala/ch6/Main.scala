package org.ProgInScala.ch6

/**
  * Created by rahul on 23/2/16.
  */
object Main extends App {

  implicit def intToRational(x: Int): Rational= new Rational(x)

  val oneHalf = new Rational(1, 2)
  val twoThirds = new Rational(2, 3)
  val five = new Rational(5, 7)

  println( oneHalf * twoThirds)
  println(oneHalf * 2)
  println(2 * oneHalf)


}
