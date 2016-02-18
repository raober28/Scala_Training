package org.impatient.ch6

/**
  * Created by rahul on 9/2/16.
  */

abstract class UnitConversion {
  def convert(value: Double) : Double
}

object InchesToCentimeters extends UnitConversion {
  def convert(inch: Double) = inch * 2.54
}

object GallonsToLiters extends UnitConversion {
  def convert(gallons: Double) = gallons * 3.78541
}

object MilesToKilometers extends UnitConversion {
  def convert(miles: Double) = miles / 1.60934
}


object Origin extends java.awt.Point {

}

object Util extends App{
  val point = Point(2, 6)

  println(point)
}



