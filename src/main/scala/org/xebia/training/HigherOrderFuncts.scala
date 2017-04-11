package org.xebia.training

import scala.annotation.tailrec

/**
  * Created by rahul on 17/3/17.
  */
object HigherOrderFuncts extends App {

  def operate(square: Int => Int, f2: Int => Int, f3: Int => Int): Int = {
    square(2) + f2(2) + f3(2)
  }


  println(operate(x => x * x, y => 5 + y, z => 7 - z))

  println(operate(_ ^ 2, _ + 5, 7 - _))


  def sum(a: Int, b: Int, c: Int): Int = a + b + c


  def execute[A, C](a: A, b: A, operation: (A, A) => C): C = operation(a, b)


  def sum(a: Int, b: Int): Int = a + b

  def sub(a: Int, b: Int): Int = a - b

  def mul(a: Int, b: Int): Int = a * b

  def div(a: Int, b: Int): Float = a / b


  println(execute(7, 5, sum))
  println(execute(7, 5, sub))
  println(execute(7, 5, mul))
  println(execute[Int, Float](7, 5, div))


  def fibonacci(n: Int): Unit = {

    def fibonacciR(a: Int, b: Int, n: Int, result: Int): Int = {
      if (n == 0) result
      else {
        print(a + " -\t")
        fibonacciR(b, b + 1, n - 1, a + b)
      }
    }

    fibonacciR(0, 1, n, 0)

  }


  fibonacci(10)

  @tailrec
  def myMap(lst: List[Int], f: Int => String, r: List[String]): List[String] = {
    lst match {
      case Nil => r
      case head :: tail => myMap(tail, f, r :+ f(head))
    }
  }

  println()
  println(myMap(List(1, 2, 3, 4, 5), x => s""""$x"""", Nil))


  def matchPerson(person: Person): Unit = {
    person match {
      case p@Person(name@"Sameer", _, _, _) if p.age == 35 => println(s"person with name $name matched")
      case p if p.age < 30 => println("person with age less than 30 matched")
      case p if p.adharNo == 0 => print("person with adhar no == 0 matched")
    }

  }
}


