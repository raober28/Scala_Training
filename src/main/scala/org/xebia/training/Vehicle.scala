package org.xebia.training

/**
  * Created by rahul on 17/3/17.
  */


object TraitBike extends App {


}

abstract class Vehicle {
  def drive(x: Int): Unit = {
    print("An abstract vehicle")
  }
}

trait Turnable {
  def turn(a: Float)
}


trait Reversable {
  def moveBack(a: Int)
}


class Car extends Vehicle with Turnable with Reversable {
  override def drive(x: Int): Unit = {}

  println("Driving a Car")

  override def turn(a: Float): Unit = ???

  override def moveBack(a: Int): Unit = ???
}

class MotorBike extends Vehicle with Turnable {
  override def turn(a: Float): Unit = ???

  override def drive(x: Int): Unit = {
    println("Driving a motorbike")
  }
}

class Tank extends Vehicle {}


