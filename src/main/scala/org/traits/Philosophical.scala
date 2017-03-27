package org.traits

/**
  * Created by rahul on 27/12/16.
  */

class Animal

trait HasLegs

trait Philosophical {

  def philosophize() {
    println("I consume memory, therefore I am!")
  }

}

class Frog extends Animal with Philosophical with HasLegs{
  override def toString = "green"
}

object Main extends App {
  new Frog().philosophize()
}
