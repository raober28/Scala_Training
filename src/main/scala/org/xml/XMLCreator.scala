package org.xml

import scala.xml.Elem

/**
  * Created by rahul on 27/3/17.
  */

abstract class CCTherm {
  val description : String
  val yearMade : Int
  val dateObtained : String
  val bookPrice : Int
  val purchasePrice: Int
  val condition : Int

  override def toString: String = description

  def toXML: Elem =
    <cctherm>
      <description>{description}</description>
      <yearMade>{yearMade}</yearMade>
      <dateObtained>{dateObtained}</dateObtained>
      <bookPrice>{bookPrice}</bookPrice>
      <purchasePrice>{purchasePrice}</purchasePrice>
      <condition>{condition}</condition>
    </cctherm>


  def fromXML(node: scala.xml.Node): CCTherm =
  new CCTherm {
     val description: String = (node \ "description").text
     val yearMade: Int = (node \ "yearMade").text.toInt
     val dateObtained: String = (node \ "dateObtained").text
     val bookPrice: Int =     (node \ "bookPrice").text.toInt
     val purchasePrice: Int =  (node \ "purchasePrice").text.toInt
     val condition: Int =     (node \ "condition").text.toInt
  }
}



object XMLCreator extends App {
  val therm: CCTherm = new CCTherm {
    val description: String = "hot dog"
    val yearMade: Int = 1952
    val dateObtained: String = "March 14, 2006"
    val purchasePrice: Int = 500
    val condition: Int = 9
    val bookPrice: Int = 2199


  }

  def proc(node: scala.xml.Node) =
  node match {
    case <catalog>{contents @ _*}</catalog> =>
      for(content @ <cctherm>{_*}</cctherm> <- contents)
        println("processing" + (content \ "description").text)

    case _ =>
  }


  private val thermoXML = therm.toXML
  //println(thermoXML)
  private val node = therm.toXML \ "cctherm"
  //println(node)

  //println(therm.fromXML(thermoXML))

  scala.xml.XML.save("cool.xml", thermoXML)

  val file = scala.xml.XML.loadFile("cool.xml")

  //println(file)

  val catalog = scala.xml.XML.loadFile("module.xml")


  proc(catalog)

  catalog.attribute("s")

}
