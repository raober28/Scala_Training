package org.ProgInScala.ch6

import scala.collection.immutable.StringOps

/**
  * Created by rahul on 23/2/16.
  */
object Main extends App {

  implicit def intToRational(x: Int) : Rational = new Rational(x)

  val x = new Rational(1, 2)
  val y = new Rational(2, 3)

  x + y

  val lst = List(1, 2, 3, 4, 5, 6)
  lst.reduce((x , y) => x + y)
 x * 2

  2 * x

  def ech(msg: String*) = msg foreach println

  "zl,klkjsdn"
  .toInt
  new StringOps("jhdsb").toInt




}
