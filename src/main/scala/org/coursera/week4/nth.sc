package org.coursera
import week4._

object nth {
  def nth[T](n: Int, lst: List[T]): T = {
    if (lst.isEmpty) throw new IndexOutOfBoundsException("index out of bound")
    else if (n == 0) lst.head
    else nth(n - 1, lst.tail)
  }



  val list = new Cons(1, new Cons(2, new Cons(3, new Nil)))

  println(nth(-1, list))








}