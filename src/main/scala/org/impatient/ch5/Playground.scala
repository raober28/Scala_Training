package org.impatient.ch5

/**
  * Created by rahul on 8/2/16.
  */



import scala.beans.BeanProperty
import scala.collection.mutable.ArrayBuffer

class Network {
  class Member(val name : String) {
    val contacts = new ArrayBuffer[Member]
  }

  private val members = new ArrayBuffer[Network#Member]

  def join(name: String) = {
    val m = new Member(name)
    members += m
    m
  }
}

class Counter {
  private var value = 0
  def increment() = if( value < Int.MaxValue) value += 1
  def current = value
}


class BankAccount(private  var balance: Long = 0) {

  def withdraw(amt: Long) = {
    if(amt < balance) { balance -= amt;  println("Remaining Balance :" + Balance);0 }
    else { println("error: Insufficient Balance"); -1 }
  }

  def deposit(amt: Long)  = {
    if(balance + amt > Long.MaxValue) { println("error: Cross Max Limit"); -1 }
    else { balance += amt; println("Remaining Balance :" + Balance); 0 }
  }

  def Balance = balance

}

class Time(private var _hours: Int = 0, private var _min: Int = 0)
{
  if(_hours <  0)     _hours =  0
  if(_hours > 23)     _hours =  23
  if(_hours <  0)     _min   =  0
  if(_min >   59)     _min   =  59

  def time = _hours * 60 + _min

  def hours = _hours
  def minutes = _min

  def before(other : Time): Boolean = {
    if(this.time < other.time) true
    else false
  }

}


class Student(@BeanProperty var name : String,@BeanProperty var id: Long) {

}

class Person(name : String) {

  val fullName =  name.split(" ")
  if(fullName.length < 2) { throw new IllegalNameException("Insufficient Name Field") }
  var fname: String = fullName(0)
  var lname: String = fullName(1)

}
class IllegalNameException(msg: String) extends Exception(msg) {}

class Car(val manufacture : String, val model : String, val year: Int, var plate: String) {

  def this(manufacturer: String, model: String) = {
    this(manufacturer, model, -1, "")
  }

  def this(manufacturer: String, model: String, year : Int) = {
    this(manufacturer, model, year, "")
  }

  def this(manufacturer: String, model: String, plate: String) = {
    this(manufacturer, model, -1, plate)
  }

}

class Employee {
  private[this] var name : String = "John Q. Public"
  var Salary : Double = 0.0

  def this(name: String, Salary: Double) = {
    this
    this.name = name
    this.Salary = Salary
  }
}

object Playground {

  val sbi = new BankAccount

  def main(args: Array[String]) {

    val p = new Person("Karan Syal")
    println(p.fname + ":" + p.lname)

  }




}

