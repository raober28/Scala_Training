package org.akka.rerun

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by rahul on 17/5/17.
  */
object ReRun {


  def main(args: Array[String]): Unit = {
    forFuture()
  }

  val oneFuture: Future[Int] = Future {
    Thread.sleep(1000)
    1
  }

  def checkState(): Unit = {
    println("Before the job finishes")
    Thread.sleep(500)
    println(s"Completed : ${oneFuture.isCompleted}, Value : ${oneFuture.value}")

    println("After the job finishes")
    Thread.sleep(1100)
    println(s"Completed : ${oneFuture.isCompleted}, Value : ${oneFuture.value}")

  }


  def forFuture() = {

    val fut1 = Future {
      Thread.sleep(10000);
      21 + 21
    }

    val fut2 = Future {
      Thread.sleep(10000);
      21 / 0
    }


    val result = for {
      x <- fut2
      y <- fut1
    } yield x + y

    Thread.sleep(10000)
    println(s"result is ${result.isCompleted}")
    println(s"result is ${result.value}")

  }

}
