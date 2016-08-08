package org.ProgInScala.GOT

/**
  * Created by rahul on 9/3/16.
  */

//Legacy
abstract class Lannister {
  def payTheirDebits: Boolean
  def trueLannister = payTheirDebits
}

//Father
trait Tywin extends Lannister {
  override def payTheirDebits = true
  def debt : Int
  def addToDebt(amount: Int) = debt + amount
}

// Son #1
trait Jamie extends Tywin {
  override def payTheirDebits = true
  override def addToDebt(amount: Int) = super.addToDebt(2*amount)
}


// Son #2
trait Tyrion extends Tywin {
  override def payTheirDebits = true
  override def addToDebt(amount: Int) = super.addToDebt(amount)
}

trait Cersei extends Tywin {
  override def payTheirDebits = false
  override def addToDebt(amount: Int) = super.addToDebt(amount / 4)
}

class Joffrey extends Lannister with Jamie with Cersei {
  def debt = 100
  override def addToDebt(amount: Int) = debt * 10
}

class Tommen extends Lannister with Cersei with Jamie {
  def debt = 200
}

class Leon extends Tyrion {
  def debt = 300
}

object GamesOfThrones extends App {
  val joffrey = new Joffrey
  println(joffrey.addToDebt(100))

  val tommen = new Tommen
  println(tommen.addToDebt(100))

  val leon = new Leon with Jamie
  println(leon.addToDebt(100))
}

