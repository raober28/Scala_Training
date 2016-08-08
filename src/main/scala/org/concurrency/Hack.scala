package org.concurrency


import scala.concurrent.{Await, Future}
import scala.util.{Success, Failure}

/**
  * Created by rahul on 30/6/16.
  */
object Hack {

  def main(args: Array[String]) {
    printFuture(oneFuture)
    printFuture(oneDangerousFuture)

    synchronized(wait(3000))

  }
  import scala.concurrent.ExecutionContext.Implicits.global

  val oneFuture = Future {
    Thread.sleep(1000)
    1
  }

  val oneDangerousFuture = Future {
    Thread.sleep(2000)
    throw new SomeComputationException("Welcome to Dark Side !")
  }

  def checkState(): Unit = {
    println("Before the job finishes")
    Thread.sleep(500)
    println(s"Completed : ${oneFuture.isCompleted}, Value : ${oneFuture.value}")

    println("After the job finishes")
    Thread.sleep(1100)
    println(s"Completed : ${oneFuture.isCompleted}, Value: ${oneFuture.value}")

    Await
  }


  def printFuture[T](future: Future[T]): Unit = future.onComplete {
    case Success(result) => println(s"Success $result")
    case Failure(throwable) => println(s"Failure $throwable")
  }


}

class SomeComputationException(msg: String) extends Exception(msg)
