package org.xml

import scala.xml.{Elem, Node}

/**
  * Created by rahul on 27/3/17.
  */
object IncidentParser {
  def parseIncident(xml: Elem): Elem = {
    val response = scala.xml.XML.loadFile("new-incident-response-1.xml")
    response
  }

}
