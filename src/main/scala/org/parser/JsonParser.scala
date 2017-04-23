package org.parser

import scala.util.parsing.json.JSON

import play.api.libs.json._

/**
  * Created by rahul on 22/4/17.
  */
class JsonParser {

  val json: JsValue = Json.parse(
    """
      |{
      |  "name" : "Watership Down",
      |  "location" : {
      |    "lat" : 51.235685,
      |    "long" : -1.309197
      |  },
      |  "residents" : [ {
      |    "name" : "Fiver",
      |    "age" : 4,
      |    "role" : null
      |  }, {
      |    "name" : "Bigwig",
      |    "age" : 6,
      |    "role" : "Owsla"
      |  } ]
      |}
    """.stripMargin)


  json.copy()

  val jsonCons: JsValue = JsObject(Seq(
    "name" -> JsString("Watership down"),
    "location" -> JsObject(Seq("lat" -> JsNumber(50.235685), "long" -> JsNumber(-1.309197))),
    "residents" -> JsArray(Seq(
      JsObject(Seq(
        "name" -> JsString("Fiver"),
        "age" -> JsNumber(4),
        "role" -> JsNull
      )),
      JsObject(Seq(
        "name" -> JsString("Bigwig"),
        "age" -> JsNumber(6),
        "role" -> JsString("Owsla")
      ))
    ))
  ))


  val jsonSimple: JsValue = Json.obj(
    "name" -> "Watership down",
    "location" -> Json.obj("lat" -> 50.235685, "long" -> -1.309197),
    "residents" -> Json.arr(Json.obj("name" -> "Fiver", "age" -> 4, "role" -> null),
      Json.obj("name" -> "Bigwig", "age" -> 6, "role" -> "Owsla"))
  )




}
