package org.concurrency

import org.scalatest.{Matchers, FunSpec, FunSuite}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by rahul on 1/7/16.
  */
class FutureCombinatorsTest extends FunSpec with Matchers with ConcurrentUtils {
  describe("Futures") {
    it("could be composed using map") {
      val futureCombinators = new FutureCombinators()
      val result = timed(Await.result(futureCombinators.sumOfThreeNumbersSequentialMap(), 7 seconds))
      result shouldBe 6
    }


    it("could be composed using for comprehension") {
      val futureCombinators = new FutureCombinators
      val result = timed(Await.result(futureCombinators.sumOfThreeNumbersParllel(), 4 seconds))
      result shouldBe 6
    }
  }


}


trait ConcurrentUtils {
  def timed[T](block: => T ): T = {
    val start = System.currentTimeMillis()
    val result = block
    val duration = System.currentTimeMillis() - start
    println(s"Time taken $duration")
    result
  }
}
