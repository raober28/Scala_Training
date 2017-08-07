package org.collections

import scala.collection.mutable

/**
  * List are immutable
  * List have recursive structure
  */
object My_List extends App {
  val fruit = List("apple", "mango", "banana")
  val abc = List()
  val num = 1 :: 2 :: 3 :: 4 :: 5 :: Nil

  val a :: b :: rest = fruit

  def concat[T](lst1: List[T], lst2: List[T]) = {
    def concatR(lst1: List[T]): List[T] = lst1 match {
      case Nil => lst2
      case head :: tail => head :: concatR(tail)
    }

    concatR(lst1)
  }


  val lst1 = List(1, 2, 3, 4)
  var lst2 = List(5, 6, 7, 8)

  lst2 ++= List(456)
  List(List(1, 2), List(3), List(), List(4, 5)).flatten
  println(List.range(1, 5))

  println(List.range(1, 5) map (i => List.range(1, i)))
  println(List.range(1, 5) flatMap (i => List.range(1, i)))
  println(List.range(1, 5) flatMap (i => List.range(1, i)))

  println(List.range(1, 5) map (i => List.range(1, i) map (j => (i, j))))
  println(List.range(1, 5) flatMap (i => List.range(1, i) map (j => (i, j))))

  val fiveInts = new Array[Int](5)
  val fiveToOne = Array(5, 4, 3, 2, 1)

  val buf = new mutable.ListBuffer[Int]
  val arrBuf = new mutable.ArrayBuffer[Int]()
  arrBuf += 12
  arrBuf += 15

  val text = "See Spot run. Run, Spot. Run!"

  val wordsArray = text.split("[ !,.]+")
  buf += 1
  buf += 2
  3 +=: buf

  var ab = 4

  ab += 2

  println(buf.toList)

  val words = mutable.Set.empty[String]
  (0 /: lst1) (_ + _)

  for (word <- wordsArray)
    words += word.toLowerCase
  val nums = Set(1, 2, 3)
  nums + 5
  nums - 3

  val stuff = mutable.Set[Any](42)

  words += "hello"


  /*concat(lst1, lst2) foreach print*/


  case class Person(name: String, isMale: Boolean, children: Person*)

  val lara = Person("Lara", false)
  val bob = Person("Bob", true)
  val julie = Person("Julie", false, lara, bob)
  val persons = List(lara, bob, julie)

  println(persons)
  println(persons filter(p => !p.isMale))
  println(persons filter(p => !p.isMale)map (p => p.children map( c => (p.name, c.name))))
  println(persons filter(p => !p.isMale) flatMap  (p => p.children map( c => (p.name, c.name))))
  println(persons withFilter (p => !p.isMale) flatMap  (p => p.children map( c => (p.name, c.name))))

  for(p <- persons; if !p.isMale; c <- p.children)
    yield (p.name, c.name)

}
