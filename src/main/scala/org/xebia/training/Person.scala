package org.xebia.training

/**
  * Created by rahul on 17/3/17.
  */
case class Person(name: String, age: Int,  adharNo : Int, designation: String)

object Person {

  def apply(name: String, age: Int): Person = Person("rahul", 35, 1, "Computer Scientist")
  def apply(name: String): Person  = Person("Sammer", 30, 2, "Programmer")
}
