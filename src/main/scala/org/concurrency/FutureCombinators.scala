package org.concurrency

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by rahul on 1/7/16.
  */
class FutureCombinators {

  val oneFuture = Future {
    Thread.sleep(1000)
    1
  }

  val twoFuture = Future {
    Thread.sleep(2000)
    2
  }

  val threeFuture: Future[Int] = Future {
    Thread.sleep(3000)
    3
  }

  def sumOfThreeNumbersSequentialMap(): Future[Int] = {
    Future {
      Thread.sleep(1000)
      1
    }.flatMap { oneValue =>
      Future {
        Thread.sleep(2000)
        2
      }.flatMap { twoValue =>
        Future {
          Thread.sleep(3000)
          3
        }.map { thirdValue =>
          oneValue + twoValue + thirdValue
        }
      }
    }
  }


  def sumOfThreeNumbersParllel(): Future[Int] = {
    for {
      oneValue <- oneFuture
      twoValue <- twoFuture
      threeValue <- threeFuture
    } yield oneValue + twoValue + threeValue
  }
}
