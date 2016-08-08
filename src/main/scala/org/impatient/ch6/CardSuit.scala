package org.impatient.ch6

import org.impatient.ch6.CardSuit.CardSuit

/**
  * Created by rahul on 10/2/16.
  */
object CardSuit extends Enumeration {
  type CardSuit = Value
  val Spades   = Value("\u2600")
  val Hearts    = Value("\u2665")
  val Diamonds = Value("\u2666")
  val Clubs    = Value("\u2663")

}


object util extends App {

  import CardSuit._
  println("Spades   = " + CardSuit.Spades)
  println("Hearts   = " + CardSuit.Hearts)
  println("Diamonds = " + CardSuit.Diamonds)
  println("Clubs    = " + CardSuit.Clubs)


  def isRed(suits: CardSuit): Boolean = {
    suits == Diamonds || suits == Hearts
  }

  CardSuit.values.foreach(suit => println("isRed(" + suit + ")=" + isRed(suit)))

  println(CardSuit.values.mkString("<", " ", ">"))
}
