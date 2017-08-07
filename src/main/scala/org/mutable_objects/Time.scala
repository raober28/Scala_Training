package org.mutable_objects

/**
  * Created by rahul on 1/7/17.
  */
class Time {
  private[this] var h = 12
  private[this] var m = 0

  def hour : Int = h
  def hour_= (x : Int) = {
    require(0 <= x && x < 24)
    h = x
  }

  def min : Int = m
  def min_= (y : Int) = {
    require(0 <= y && y < 24)
  }

}

class Thermometer {
  var celsius : Float = _

  def fahrenheit = celsius * 9 / 5 + 32
  def fahrenheit_= (f: Float) = {
    celsius = (f - 32) * 5 / 9
  }

  override def toString: String = fahrenheit + "F/" + celsius + "C"
}
