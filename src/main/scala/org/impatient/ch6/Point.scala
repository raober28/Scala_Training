package org.impatient.ch6

/**
  * Created by rahul on 9/2/16.
  */
class Point(val x: Int, val y: Int) {

}

object Point {
  def apply(x: Int, y: Int) = new Point(x, y)
}


