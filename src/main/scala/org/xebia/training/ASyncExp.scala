package org.xebia.training

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by rahul on 18/3/17.
  */
object ASyncExp extends App {

  eventualInt(2)

  private def eventualInt(i: Int)  = {
    Future {
      Thread.sleep(1000)
      i + 1
    }
  }


}
