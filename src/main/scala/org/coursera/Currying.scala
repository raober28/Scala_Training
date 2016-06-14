package org.coursera

/**
  * Created by rahul on 13/6/16.
  */
object Currying {



  def product(f: Int => Int)(a: Int, b: Int): Int = {
    if(a > b) 1
    else  f(a) * product(f)(a + 1, b)
  }



  def main (args: Array[String]) {
    println(exponent(x => x * x)(1,5))
  }

}
