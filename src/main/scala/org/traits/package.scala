package org

/**
  * Created by rahul on 18/3/17.
  */
package object traits {

  implicit val i: Int = 10

  implicit def f(i: Int) : String = i.toString

}
