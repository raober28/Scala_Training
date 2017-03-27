package org.impatient.ch4

import java.util.Scanner

import scala.collection.immutable.SortedMap
import scala.collection.JavaConversions.{mapAsScalaMap, propertiesAsScalaMap}


/**
  * Created by rahul on 8/2/16.
  */
object map {

  val header = "=" * 10 + "Chapter 4: Exercise %1$02d" + "=" * 10
Int
  def main(args: Array[String]) {
    println(leeqgt(Array(45, 78, 21, 63, 24, 56, 58, 24, 23, 18, 17, 65, 4, 595), 36))
  }

  /*discount : Discount 10% on all items*/
  def discount() = {
    println(header.format(1))
    val gizmos = Map("s6" -> 45000, "6s" -> 42000, "m6" -> 4000)
    val discountedGizmos = for((key, value) <- gizmos) yield key -> (value - value * 0.1)

    println("Original Price: " + gizmos.mkString("<", ", " , ">"))
    println("Discount Rate : " +  discountedGizmos.mkString("<", ", " , ">"))
  }

  /*countWords : Count No of Occurrence of each word in a file*/
  def countWords() = {
    println(header.format(2))
    val in = new Scanner(new java.io.File("sample.txt"))
    val words = collection.mutable.Map[String, Int]()
    while(in.hasNext)
      {
        val word = in.next
        words(word) = words.getOrElse(word, 0) + 1
      }

    println(words)
  }

  /*countWord_V2 : count no of occurrence of each word using immutable Map*/
  def countWord_ImmutableMap() = {
    println(header.format(3))
    val in = new Scanner(new java.io.File("sample.txt"))
    var words = Map[String, Int]()

    while(in.hasNext)
      {
        val word = in.next
        words +=  word ->  (words.getOrElse(word, 0) + 1)
      }

    println(words)

  }


  def countWord_SortedMap() = {
    println(header.format(4))
    val in = new Scanner(new java.io.File("sample.txt"))
    var words = SortedMap[String, Int]()

    while(in.hasNext)
    {
      val word = in.next
      words +=  word ->  (words.getOrElse(word, 0) + 1)
    }

    println(words)

  }

  def countWord_TreeMap() = {
    println(header.format(5))
    val in = new Scanner(new java.io.File("sample.txt"))
    val words : scala.collection.mutable.Map[String, Int] = new java.util.TreeMap[String, Int]

    while(in.hasNext)
    {
      val word = in.next
      words(word) = words.getOrElse(word, 0) + 1
    }

    println(words)

  }

  def weekends() = {
    println(header.format(6))
    val weekend = scala.collection.mutable.LinkedHashMap("Monday" -> java.util.Calendar.MONDAY ,
    "Tuesday" -> java.util.Calendar.TUESDAY,
    "Wednesday" -> java.util.Calendar.WEDNESDAY,
    "Thursday" -> java.util.Calendar.THURSDAY,
    "Friday" -> java.util.Calendar.FRIDAY,
    "Saturday" -> java.util.Calendar.SATURDAY,
    "Sunday" -> java.util.Calendar.SUNDAY
    )

    println(weekend)

  }


  def print_properties() = {
    println(header.format(7))
   val properties : scala.collection.mutable.Map[String, String]   = System.getProperties
   val maxLength = properties.keys.maxBy(_.length).length

    for((key, value) <- properties ) println(s"%-${maxLength}s | %s".format(key, value))

  }

  def minmax(values : Array[Int]) = {

    (values.min, values.max)
  }


  /*leeqgt: returns a tuple containing count of array elements that are less than, equal to, greater than v*/
  def leeqgt(values : Array[Int], v: Int) = {
    println(header.format(8))

    val lt = for(elem <- values if elem <  v ) yield elem
    val gt = for(elem <- values if elem >  v ) yield elem
    val et = for(elem <- values if elem == v ) yield elem


    (lt.length, et.length, gt.length)

  }

  def sum(a: Int, b: Int) = {


  }

  def sum(value: List[Int]): Int = {
    value match {
      case h :: t => h + sum(t)
      case _ => 0
    }
  }


}
