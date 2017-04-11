package org.xebia
package training

/**
  * Created by rahul on 18/3/17.
  */
object ExplicitImplicit extends App {

  def action(a: Int)(implicit f: Int => String, i : Int): String = {
    println(a)
    f(a + i)
  }


  import org.traits._
  println(action(5))

}
