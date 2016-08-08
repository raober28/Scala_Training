package org.random.parser

import scala.util.parsing.combinator.Parsers
import scala.util.parsing.input.CharSequenceReader

/**
  * Created by rahul on 14/7/16.
  */
object  MyOwnParser extends Parsers {

  type Elem = Char
  val aParser = new Parser[Char] {
    def apply(in: Input): ParseResult[Char] = {
      val c = in.first

      if(c == 'a') Success(c, in.rest)
      else  Failure("Expected 'a' got '" + c + "'", in)
    }
  }

  def charParser(expected: Char) = new Parser[Char] {
    def apply(in: Input):ParseResult[Char] = {
      val c = in.first
      if(c == expected) Success(c, in.rest)
      else  Failure("Expected '" + expected + " 'got '" + c + "'", in)
    }
  }

  val ab01 = charParser('a') or charParser('b')  or charParser('0') or  charParser('1')

  abstract class Parser[T] extends super.Parser[T] {
    def or(right: Parser[T]):Parser[T] = {
      val left = this
      new Parser[T] {
        def apply(in:Input) =
        left(in) match {
          case s: Success[T] => s
          case _ => right(in)
        }
      }
    }
  }

  def   repeat[T](p: Parser[T]) = new Parser[List[T]] {
    def apply(in: Input): Success[List[T]] = {
      p(in) match {
        case Success(t, next) => val s = apply(next)
                                 Success(t::s.get, s.next)


        case _ => Success(Nil, in)

      }
    }
  }

  val myParser = repeat(ab01)

  def  run(s: String): Option[List[Char]] = {
    val input = new CharSequenceReader(s)

    myParser(input) match {
      case Success(list, next) if next.atEnd =>  Some(list)
      case _ => None
    }
  }

  def main(args: Array[String]) {
    println(MyOwnParser.run("ab10"))
    println(repeat(ab01)(new CharSequenceReader("ab10lallo1")))

  }


}



trait Ab01ProperParsers extends Parsers {
  type Elem = Char
  //"elem" parses exactly one element of the defined "Elem" type.
  //In our case this is a Char, just like our manual Parser.

  //The "|" Combinator is the same as our "or" Combinator
  //so this reads "element 'a' or element 'b' or ..."
  val ab01:Parser[Char] = elem('a') | elem('b') | elem('0') | elem('1')

  //The postfix "*" Combinator repeats the Parser it as applied to as often as possible.
  //The asterisk was chosen as this works like the Kleene Star
  val myParser:Parser[List[Char]] = ab01*
}

