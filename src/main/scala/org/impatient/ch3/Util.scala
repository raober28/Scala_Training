
package org.impatient.ch3

import scala.collection.mutable.{ArrayBuffer, Buffer}
import scala.util.Random
import java.awt.datatransfer._
import scala.collection.JavaConversions.asScalaBuffer

/**
  * Created by rahul on 5/2/16.
  */
object Util {

  val header = "=" * 10 + "Chapter 3: Exercise %1$02d" + "=" * 10

  def main(args: Array[String]) {
    collection_conversion()

  }

  //remove_AllButFirstNegative : remove all but the first negative number
  def remove_AllButFirstNegative(arr: ArrayBuffer[Int]) = {
    var first = true                            //Flag to mark the first negative number

                                                /*Collect all Indexes having all but first -ve values */
    val indexes = for (i <- arr.indices if first || arr(i) > 0) yield {
      if (arr(i) < 0) first = false
      i
    }
    for (i <- indexes.indices)
      arr(i) = arr(indexes(i))
    arr.trimEnd(arr.length - indexes.length)
  }

  /*fill_RandomArray : generates an array of random size with random values*/
  def fill_RandomArray() = {
    println(header.format(1))
    val num = Random.nextInt(20)
    val arr = new Array[Int](num)

    for(i <- arr.indices) arr(i) = Random.nextInt(num)

    println(arr.mkString("[", ", ", "]"))
  }

  /*swap_Array: Swap Adjacent elements of Array*/
  def swap_Array() = {
    println(header.format(2))
    var temp = 1
    val arr = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(arr.mkString("[", ", ", "]"))
    for(i <- arr.indices) {
      if(i % 2 != 0)
        {
          temp = arr(i)
          arr(i) = arr(i -1)
          arr(i - 1) = temp
        }
    }

    println("Swapped Array :" + arr.mkString("[", ",", "]"))
  }

  /*swapArray_v2: Generates new Array by swapping */
  def swap_Array_v2() = {
    println(header.format(3))
    val arr = Array(1,2,3,4,5,6,7,8,9)

    val newArray = for(i <- arr.indices) yield {
      if(i % 2 == 0) if(i + 1 < arr.length) arr(i + 1) else arr(i)
      else arr(i - 1)
    }

    println("Original Array : " + arr.mkString("[", ", ", "]"))
    println("Swapped Array  : " + newArray.mkString("[", ", " , "]"))
  }


  /*rearrange : shift all positive, negative and zero elements together in original order */
  def rearrange() = {
    println(header.format(4))
    val arr = Array(25, -23, 78, -87, 49, 0, 25, -64, 0)
    println("Original Array   : " + arr.mkString("[", ", " , "]"))

    val positive_array = for(i <- arr.indices  if arr(i) >   0) yield arr(i)
    val negative_array = for(i <- arr.indices  if arr(i) <   0) yield arr(i)
    val zero_array     = for(i <- arr.indices  if arr(i) ==  0) yield arr(i)

    for(i <- positive_array.indices) arr(i) = positive_array(i)
    for(i <- negative_array.indices) arr(positive_array.length +  i) = negative_array(i)
    for(i <- zero_array.indices)     arr(positive_array.length + negative_array.length   +  i) = zero_array(i)

    println("Rearranged Array : " + arr.mkString("[", ", " , "]"))

  }

  /*avg: calculates avgerage of an array of double*/
  def avg(): Unit = {
    println(header.format(5))
    val arr = Array(1.2, 2.8, 12.5, 14.6, 28.4, 56.89)
    val avg = arr.sum / arr.length
    println("Average of " + arr.mkString("[", "," ,"]" ) + " : " + avg)
  }


  def reverse_sort() = {
    println(header.format(6))
    val arr = Array(65, 74, 21, 895, 24, 32, 13 ,5, 6, 67)
    println(arr.sorted.reverse.mkString("[", ", ", "]"))

    val arrB = ArrayBuffer(15,2,31,40)
    println(arrB.sorted.reverse.mkString("[", ", ", "]"))
  }

  def distinct() = {
    println(header.format(7))
    val arr = Array(12, 45, 61 ,12, 45, 8, 61, 48, 62, 61)
    println("Distinct Array Elements : "  +  arr.distinct.mkString("[", ", ", "]"))
  }

  def remove_negative() = {
    println(header.format(8))
    val arr = ArrayBuffer(14, -58, 53, -24, 7, 26, -25, 23, -8, 95, -72, 13, 68)

    println("Original Array : " + arr)

    val negative_indexes = (for(i <- arr.indices if arr(i) < 0) yield i).reverse.dropRight(1)

    for(i <- negative_indexes.indices ) arr.remove(negative_indexes(i))

    println("Transformed Array : " + arr)

  }

  def getTimeZone() = {
    println(header.format(9))
    val times = java.util.TimeZone.getAvailableIDs().filter(_.startsWith("India")).map(_.stripPrefix("Indian/")).sorted
    println(times.mkString("[", ", ", "]"))
  }

  def collection_conversion() = {
    println(header.format(10))
    val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
    val fl: Buffer[String] = flavors.getNativesForFlavor(DataFlavor.imageFlavor)
    println(fl.getClass + ":" + fl)

  }

}
