package org.random.parser

import org.scalatest.{Matchers, FlatSpec}

/**
  * Created by rahul on 13/7/16.
  */
class ReallySimpleParser$Test extends FlatSpec with Matchers {
  val parsers = ReallySimpleParser

  import ReallySimpleParser.hello

  "hello" should  "pass" in {
      hello should equal ("hello")

  }

}
