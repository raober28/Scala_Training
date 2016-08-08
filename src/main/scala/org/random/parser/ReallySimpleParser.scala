package org.random.parser


import scala.util.parsing.combinator.RegexParsers

/**
  * Created by rahul on 13/7/16.
  */
object ReallySimpleParser extends RegexParsers  {
  def hello = "hello"
}

object NumberParser extends RegexParsers {
  def number: Parser[Int] = """-?\d""".r  ^^  { _.toInt }
}
