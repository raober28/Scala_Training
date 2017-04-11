package org.xml

import org.scalatest.{BeforeAndAfterEach, FlatSpec}

/**
  * Created by rahul on 27/3/17.
  */
class IncidentParserTest extends FlatSpec with BeforeAndAfterEach {

  import IncidentParser._

  override def beforeEach() {

  }

  override def afterEach() {

  }

  behavior of "IncidentParserTest"

  "A Request to Create New Incident " should "fail " in {
    val request = xml.XML.loadFile("new-incident-message-1.xml")
    println(request.copy("ksjdn"))

    val response = parseIncident(request)

    assert(response.label === "incidentaanmeldingresponse")
    assert(response \ "dossiernummerac" === "abc")
    assert(response \ "dossiernummerac" === "ads")
    assert(response \ "dossiernummerac" === "ads")
  }

}
