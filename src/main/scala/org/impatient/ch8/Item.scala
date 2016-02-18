package org.impatient.ch8

/**
  * Created by rahul on 18/2/16.
  */
abstract class Item {

  def price() : Int
  def description() : String
}


class SimpleItem(val price : Int, val description: String) extends Item  {}

class Bundle(val description: String) extends Item {
  private val items: List[Item] = List.empty

  def addItem(item: Item) = {
    item :: items
  }

  override def price(): Double = {
    items.map(_.price()).sum
  }
}