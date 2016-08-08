package org.concurrency

import java.util.concurrent.Executors


import scala.concurrent.{Future, Promise}
import scala.util.{Success, Failure}
import scala.util.control.NonFatal

/**
  * Created by rahul on 30/6/16.
  */
class PromiseInternals {
  val somePool=Executors.newFixedThreadPool(2)

  def someExternalDelayedCalculation(f:()=>Int): Future[Int] = {
    val promise=Promise[Int]()
    val thisIsWhereWeCallSomeExternalComputation = new Runnable {
      override def run() ={
        promise complete {
          try Success(f())
          catch { case NonFatal (msg)=> Failure(msg) }
        }
      }
    }

    somePool.execute(thisIsWhereWeCallSomeExternalComputation)
    promise.future
  }


  def aCompletedPromiseUsingSuccess(num: Int): Future[Int] = {
    val promise = Promise[Int]()
    promise.success(num)
    promise.future
  }

}
