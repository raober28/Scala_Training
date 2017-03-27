package org.xebia.training

import scala.concurrent.Future

/**
  * Created by rahul on 18/3/17.
  */
object ASyncExp {

  eventualInt(2)

  private def eventualInt(i: Int)  = {
    Future {
      Thread.sleep(1000)
      i + 1
    }
  }


}
