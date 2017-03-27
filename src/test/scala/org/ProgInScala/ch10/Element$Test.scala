package org.ProgInScala.ch10

import org.scalatest.FunSuite
import Element.elem

/**
  * Created by rahul on 3/3/17.
  */
class Element$Test extends FunSuite {
  test("elem result should have passed width") {
    val ele = elem('x', 2, 3)
    assertResult(2){
      ele.width
    }
  }

  test("elem should throw error in case of negative values") {
    intercept[IllegalArgumentException]{
      elem('x', -2, 3)
    }
  }
}
