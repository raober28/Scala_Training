package org.parser

import org.UnitSpec
import play.api.libs.json._

/**
  * JsonTransformer Spec
  */
class JsonTransformerTest extends UnitSpec {

  val source = scala.io.Source.fromFile("sample.json")
  val actualJson = Json.parse(source.mkString("")).as[JsObject]

 "A JsonTransformer " should "return same json if the key to update not found" in {
   val updatedJson = JsonTransformer updateJson(actualJson, "random", JsNull)
   assert(actualJson.canEqual(updatedJson))
   assert(actualJson.equals(updatedJson))
 }

 /* "A JsonTransformer" should  "throw an error when the value to update is not of the same type" in {
    val key = "key23"
    val new_value = JsString("new")

    val key2 = "key24"

   intercept[InvalidJsonFormatException]{
      JsonTransformer.updateJson(actualJson, key, new_value)
    }

  intercept[InvalidJsonFormatException]{
    JsonTransformer.updateJson(actualJson, key2, new_value)
    }
  }
*/
  "A JsonTransformer" should "be able modify top level key" in {
    val key = "key1"
    val new_value = JsString("cool")
    val updatedJson = JsonTransformer.updateJson(actualJson, key, new_value)

    assert(actualJson.canEqual(updatedJson))
    assert(!actualJson.equals(updatedJson))
    assert((updatedJson \ key).as[JsString] == new_value)
  }

  "A JsonTransformer" should "be able to modify two level deep key" in {
    val key = "key22"
    val new_value = JsBoolean(false)
    val updatedJson = JsonTransformer.updateJson(actualJson, key, new_value)

    assert(!actualJson.equals(updatedJson))
    assert((updatedJson \\ key).head.as[JsBoolean] == new_value)
  }



  "A JsonTransformer" should "be able to modify three level deep key" in {
    val key = "key241"
    val new_value = JsNumber(123.234)
    val updatedJson = JsonTransformer.updateJson(actualJson, key, new_value)

    println(Json.prettyPrint(updatedJson))
    assert(!actualJson.equals(updatedJson))
    assert((updatedJson \\ key).head.as[JsNumber] == new_value)
  }

  "A JsonTransformer" should "be able to modify an nested key having array as value" in {
    val key = "key23"
    val new_value = Json.arr("x", "y", "z")
    val updatedJson = JsonTransformer.updateJson(actualJson, key, new_value)

    println(Json.prettyPrint(updatedJson))
    assert(!actualJson.equals(updatedJson))
    assert((updatedJson \\ key).head.as[JsArray] == new_value)
  }

}
