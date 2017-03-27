package org.xebia.training


object Options extends App {

  val map = Map("key1" -> "Value1", "key2" -> "Value2")


  val mayBe = map.get("key1")
  println(map.get("key1"))
  println(map.get("key2"))
  println(map.get("ke3"))

  val result = mayBe collect {
    case "Value1" => "key1"

  }

  println(result.getOrElse("default"))

  private val option = map.get("key3")

  private val map1 = option.map(x => Option(x.substring(0, 3))).map(x => Option(x.get.substring(0, 1)))

  private val map2 = option.flatMap(x => Option(x.substring(0, 3))).flatMap(x => Option(x.substring(0, 1)))


  map1.collect {
    case o => o.collect {
      case "value1" => "key1"
    }
  }

  map2.collect {
    case "v" => 2
    case "value1" => 7
  }


  // it is equivalent to flatMap
  private var maybeString = for {
    o <- option
    a <- Option(o.substring(0, 3))
    b <- Option(a.substring(0, 1))
  } yield b
  maybeString


}
