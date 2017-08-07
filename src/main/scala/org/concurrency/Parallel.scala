package org.concurrency

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationDouble
import scala.concurrent.{Await, Future}
import scala.util
import scala.util.{Failure, Success}

/**
  * Created by rahul on 14/5/17.
  */
object Parallel extends App {

  val fut = Future {
    Thread.sleep(5000);
    21 + 21
  }

  val fut2 = Future {
    Thread.sleep(5000);
    23 + 23
  }

  val fut3 = fut.flatMap(f => fut2.map(f2 => f + f2))

  Await.result(fut3, 20 seconds)

  println(fut3.value)

  val success = Future.successful {
    21 + 21
  }
  val failed = Future.failed(new Exception("bummer"))

  Future.fromTry(util.Success {
    21 + 21
  })


  fut.filter(res => res > 0)
  fut collect { case res if res > 0 => res + 46 }

  val failure = Future {
    42 / 2
  }
  val expectedFailure = failure.failed
  failure.onComplete {
    case Success(s) => s
    case Failure(e) =>
  }

  val lst = List(1, 2, 3, 4, 5, 6, 7)
  lst map {
    case head => head
    case _ => 1
  }
  //PartialFunction

  val fraction = new PartialFunction[Int, Int] {
    def apply(d: Int) = 42 / d

    def isDefinedAt(d: Int) = d != 0
  }

  val fraction1: PartialFunction[Int, Int] = {
    case d: Int if d != 0 => 42 / d
  }


  val fallback = failure.fallbackTo(success)


}
