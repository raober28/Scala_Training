package org

import java.util

/**
  * Created by rahul on 1/9/16.
  */
object Test {

  def maxDistance(arr: Array[Int]) = {
      val i = 0;
      var j = arr.length - 1;
      var result = 0
      var current = 0
    val s = new StringBuffer()


    for(i <- 0 until arr.length)
      {
        if(arr(i) == arr(j))
          {
            current = Math.abs(i - j);
            if(current > result)
              result = current
          }
        j = j - 1
      }
    result
  }

  def main(args: Array[String]): Unit = {

    var lst = List(4, 6, 2, 2, 6, 4, 1)
    var lst1 = List(4, 7, 2, 5, 9, 4, 10)

    var arr = Array(4, 7, 2, 5, 9, 4, 10)


    val common = lst.intersect(lst1)



    println(common)



  }








}
