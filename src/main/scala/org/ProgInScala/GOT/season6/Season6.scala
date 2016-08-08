package org.ProgInScala.GOT.season6

/**
  * Created by rahul on 9/3/16.
  */
// Legacy
abstract class Lannister {
  def payTheirDebts: Boolean
  def trueLannister = payTheirDebts
  def debt: Int
  def addToDebt(amount: Int): Int
}

//Father
trait Tywin extends Lannister{
  override def payTheirDebts = true
  def addToDebt(amount: Int): Int
}

// Son
trait Tyrion extends Tywin {
  override def payTheirDebts = true
  abstract override def addToDebt(amount: Int) = super.addToDebt(amount*2)
}

class Shae extends Tywin {
  def debt = 100
  def addToDebt(amount: Int) = debt + amount
}

object GameOfThrones extends App{
  val leon = new Shae with Tyrion
  println(leon.addToDebt(100)) // 300
}