package org.impatient.ch8

/**
  * Created by rahul on 10/2/16.
  */

class Person(val name: String, val age: Int) {
  override def toString = "[name " + getClass.getName + "]"

  protected def echo(s: String) = println(s)
}


class Employee(name: String, age: Int, salary: Int) extends Person(name, age) {

  override def toString = super.toString + "[salary " + salary + "]"
}

class SecretAgent(codeName: String, age: Int) extends Person(codeName, age) {

  override def toString = "secret"
  override val name = "Secret"
}

object Student {

  def main(args: Array[String]) {
   val a = new SecretAgent("bond", 45)
    println("name : " + a.name  )

  }

}
