package org.parser

/**
  * Created by rahul on 22/4/17.
  */

import play.api.libs.json._

object JsonTransformer extends App {

  val jsonSTR =
    """
      |{
      |  "key1" : "value1",
      |  "key2" : {
      |    "key21" : 123,
      |    "key22" : true,
      |    "key23" : [ "alpha", "beta", "gamma"],
      |    "key24" : {
      |      "key241" : 234.123,
      |      "key242" : "value242"
      |    }
      |  },
      |  "key3" : 234
      |}
    """.stripMargin

  val json = Json.parse(jsonSTR)


  val jsonTransformer = (__ \ 'key2 \ 'key24 \ 'key241).json.pickBranch

  val jsonTransformer1 = (__ \ 'key25 \ 'key251).json.copyFrom((__ \ 'key2 \ 'key21).json.pick)


  val jsonTransformer2 = (__ \ 'key2 \ 'key24).json.update(
    __.read[JsObject].map { o => o ++ Json.obj("key241" -> "coucou") }
  )


  val jsonTransformer3 = (__ \ 'key24 \ 'key241).json.put(JsNumber(456))

  val jsonTransformer4 = (__ \ 'key2 \ 'key22).json.prune

  val jsonTransformer5 = (__ \ 'key2).json.pickBranch(
    (__ \ 'key21).json.update(
      __.read[JsNumber].map { case JsNumber(nb) => JsNumber(nb + 10) }
    ) andThen
      (__ \ 'key23).json.update(
        __.read[JsArray].map { case JsArray(arr) => JsArray(arr :+ JsString("delta")) }
      )
  )


  println("--------1--------------------------")
  println(Json.prettyPrint(json.transform(jsonTransformer).get.as[JsValue]))

  println("\n\n--------2--------------------------")
  println(Json.prettyPrint(json.transform(jsonTransformer1).get.as[JsValue]))


  println("\n\n--------3--------------------------")
  println(Json.prettyPrint(json.transform(jsonTransformer2).get.as[JsValue]))


  println("\n\n--------4--------------------------")
  println(Json.prettyPrint(json.transform(jsonTransformer3).get.as[JsValue]))


  println("\n\n--------5--------------------------")
  println(Json.prettyPrint(json.transform(jsonTransformer4).get.as[JsValue]))


  println("\n\n--------6--------------------------")
  println(Json.prettyPrint(json.transform(jsonTransformer5).get.as[JsValue]))


  val jsonTransformer6 = json.as[JsObject].deepMerge(Json.obj("key22" -> false))

  println("\n\n--------7--------------------------")
  println(Json.prettyPrint(jsonTransformer6))



  println("\n\n----------------*--------------------------")




  def updateJson(jsObject : JsObject, key_to_update: String, new_Value : JsValue): JsObject = {

    val currentKeys = jsObject.keys
    println(s"currentKeys : $currentKeys")
    for(key <- currentKeys)
    {
      val current_value  = (jsObject \ key).get
      println(s"currentKey : $key")

      println(s"$key : ${current_value match { case v : JsObject => v ; case _ => }}")
      if(key.equalsIgnoreCase(key_to_update)) {
        /*if(current_value != (new_Value))
          throw InvalidJsonFormatException(s"supplied {$key_to_update : $new_Value}(${new_Value.getClass}) has different format than actual json {$key : $current_value}(${current_value.getClass})")
*/
        return jsObject ++ Json.obj(key_to_update -> new_Value)
      }

      if(current_value.isInstanceOf[JsObject])
        {
         return jsObject ++ Json.obj(key ->updateJson(current_value.as[JsObject], key_to_update, new_Value))
        }
    }
    jsObject
  }



  val modified_json = updateJson(json.as[JsObject], "key242", JsString("sexy"))

  println(json)
  println(modified_json)


}


case class InvalidJsonFormatException(msg: String) extends Exception(msg) {
}

