package org.impatient.ch2

/**
  * Created by rahul on 5/2/16.
  */

object Util {

  val header = "=" * 10 + "Chapter 2: Exercise %1$02d" + "=" * 10

  def main(args: Array[String]) {
    println(power(2 , -2))
  }

  def signum(n : Int) = {
    println(header.format(1))
    if(n < 0) -1 else if(n > 0) 1 else 0
  }

  def for_loop() = {
    println(header.format(4))
    for(i <- 10 to 0 by -1) println(i)
    }

  def countdown(n : Int) = {
    println(header.format(5))
    for(i <- n to 0 by -1) println(i)
  }

  def prod_unicode(str : String) = {
    println(header.format(6))
    var sum : BigInt = 1
    for(ch <- str) sum *=  ch.toInt

    sum
  }

  def prod_Unicode(str: String) = {
    println(header.format(8))

    str.map(_.toLong).product
  }

  def prod_Unicode_Recursive(str: String): Long = {
    if(str.length >= 1) str.head.toLong *  prod_Unicode_Recursive(str.tail)
    else 1
  }


  def power(x: Int, n: Int): BigDecimal = {

    var y: BigDecimal = 1

    if(n > 0 )
      if(n % 2 == 0)  { y = power(x, n/2); y * y }    //Positive and Even
      else x * power(x, n-1)                          //Positive and Odd

    else if(n == 0) 1

    else 1 / power(x,-n)                              //Negative
  }


}
