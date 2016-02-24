package org.ProgInScala.ch6

/**
  * Created by rahul on 23/2/16.
  */
class Rational(n: Int, d: Int) {
  require(d != 0)

  private val g = gcd(n, d)
  val numer: Int = n / g
  val denom: Int = d / g



  override def toString = numer  + "/" + denom

  def +(that: Rational) : Rational = {
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )
  }

  def +(i : Int): Rational = {
    new Rational(numer + i * denom, denom)
  }

  def - (that: Rational): Rational =
    new Rational(
      numer * that.denom - that.numer * denom,
      denom * that.denom
    )

  def - (i: Int): Rational =
    new Rational(numer - i * denom, denom)

  def * (that: Rational): Rational =
    new Rational(numer * that.numer, denom * that.denom)

  def * (i: Int): Rational =
    new Rational(numer * i, denom)

  def / (that: Rational): Rational =
    new Rational(numer * that.denom, denom * that.numer)

  def / (i: Int): Rational =
    new Rational(numer, denom * i)

  def lessThan(that: Rational) = {
    this.numer * that.numer < that.numer * this.numer
  }

  def max(that: Rational) = {
    if(this lessThan that) that else this
  }

  def this(n : Int) = this(n, 1)

  private def gcd(a: Int, b: Int): Int =
    if(b == 0) a else gcd(b, a % b)

}



class Person(a: Int) {
   val age: Int = a
   def +(that: Person): Person = new Person(this.age + that.age)
   def *(that: Person): Person = new Person(this.age * that.age)

  override def toString = "Person.age : " + this.age
   }

