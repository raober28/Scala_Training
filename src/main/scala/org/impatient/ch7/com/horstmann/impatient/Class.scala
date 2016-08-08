package org.impatient
package ch7.com.horstmann.impatient

object Class extends App  {

   impatient.setSeed(5)
  println(impatient.nextInt())

  def copy() = {
    import scala.collection.mutable.{HashMap => ScalaHashMap}
    import java.util.{HashMap => JavaHashMap}
    import collection.JavaConversions._


    val javaMap: JavaHashMap[Int, String] = new JavaHashMap()
    javaMap.put(1, "one")
    javaMap.put(2, "two")
    javaMap.put(3, "three")

    var scalaMap: ScalaHashMap[Int, String] = ScalaHashMap.empty

    for( (key, value) <- javaMap) {
      println("javaMap: " + key + " -> " + value)
      scalaMap += key -> value
    }
  }

}


object system {

}






