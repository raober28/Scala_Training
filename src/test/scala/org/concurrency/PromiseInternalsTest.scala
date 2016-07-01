package org.concurrency

import org.scalatest.{Matchers, FunSpec}

import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by rahul on 30/6/16.
  */
class PromiseInternalsTest extends FunSpec with Matchers {

  describe("A Future") {
    it("gives out the correct value when a Promise is completed") {
      val promiseInternals = new PromiseInternals
      val aCompletedPromise = promiseInternals.aCompletedPromiseUsingSuccess(100)
      assertValueUsingOnComplete(aCompletedPromise, 100)
    }


    def assertValueUsingOnComplete(future: Future[Int], expectedValue: Int): Unit = {
      future.onComplete {
        case Success(result) =>
          println(s"Result is $result and expectedValue is $expectedValue")
          result shouldBe expectedValue

        case Failure(msg) => fail(msg)
      }
    }

    it("gives out the correct value when an asynchronous block is submitted " +
      "and is completed through a Promise") {
      val promiseInternals = new PromiseInternals
      val longCalculationFuture = promiseInternals.someExternalDelayedCalculation{() =>
      Thread.sleep(2000)
      100
      }

      println(s"We have submitted a block to be executed asynchronously ${longCalculationFuture.isCompleted}")
      assertValue(longCalculationFuture, 100)
    }

    def assertValue(future: Future[Int], expectedValue: Int): Unit = {
      val resultVal = Await.result(future, 100 seconds)
      resultVal shouldBe expectedValue
    }

  }

}
