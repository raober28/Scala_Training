package org.concurrency

import java.util.concurrent.TimeoutException

import org.scalatest.{FunSpec, Matchers, FunSuite}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by rahul on 30/6/16.
  */
class Hack$Test extends FunSpec with Matchers {
  describe("A Promising Future") {

    it("should hold a Int value if the Await.result is called after the Future completes") {
      val oneFuture =  Hack.oneFuture //Takes 1 sec to complete
      val intValue = Await.result(oneFuture, 2 seconds)
      intValue should be(1)
    }


    it("should propagate the Exception to the callee if the computation threw exception") {
      val oneDangerousFuture = Hack.oneDangerousFuture  //throws Exception
      intercept[SomeComputationException] {
        val intValue = Await.result(oneDangerousFuture, 2 seconds)
      }


      it("should throw a TimeoutException exception when an Await.result's atMost parameter" +
        "is lesser than the time taken for the Future to complete") {
        val oneDelayedFuture = Hack.oneFuture
        intercept[TimeoutException] {
          Await.result(oneDelayedFuture, 500 millis)
        }

      }
    }
  }

}
