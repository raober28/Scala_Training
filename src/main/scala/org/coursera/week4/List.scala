package org.coursera.week4

import java.util.NoSuchElementException

/**
  * Created by rahul on 22/6/16.
  */
trait List[T] {
  def isEmpty: Boolean
  def head : T
  def tail: List[T]
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty = false
}

class Nil[T] extends List[T] {
  def isEmpty = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}



object Main {


}