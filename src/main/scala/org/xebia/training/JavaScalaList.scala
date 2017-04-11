package org.xebia.training

import java.util

import scala.collection.convert.wrapAll._
/**
  * Created by rahul on 18/3/17.
  */
object JavaScalaList {

  def someListHandler(list: java.util.List[Int]) = {
    list.toList.map(x => x * 2)
  }

  private val arrayList: java.util.ArrayList[Int] = new util.ArrayList[Int]()

  arrayList.add(1)
  arrayList.add(2)
  arrayList.add(3)
  arrayList.add(4)

  println(someListHandler(arrayList))

}
