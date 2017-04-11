package org.xebia.training

import java.io.InputStream
import java.lang
import java.net.URL

import scala.io.Source
import scala.util.{Failure, Success, Try}

/**
  * Created by rahul on 18/3/17.
  */
object TryUasge extends App {

  def buyCigarettes(age: Int) : Try[String]  = Try {
    if (age < 20)
      throw new IllegalArgumentException(s"Customer must be older than 20 but was found $age")
    else
      "Bought"
  }

  private val cigarettes = buyCigarettes(16)

  if (cigarettes.isFailure)
    println("The bid does not succeed")

  /* cigarettes match {
     case Success(x) => println(x)
     case Failure(ex) => throw new lang.IllegalArgumentException
   }*/

  private def parseUrl(url: String) = {
    Try {
      new URL(url)
    }
  }


  val url = parseUrl("https://www.quora.com/").getOrElse(new URL("https://www.quora.com/"))

  private val map: Try[Try[String]] = parseUrl("https://www.quora.com/").map(url => Try(url.getProtocol))

  def inputStream(url: String): Try[Try[Try[InputStream]]] = parseUrl(url).map { u =>
    Try(u.openConnection()).map(conn => Try(conn.getInputStream))
  }


  private def inputStramForURLFMap(url: String): Try[InputStream] = {
    parseUrl(url).flatMap(url => Try(url.openConnection)).flatMap(conn => Try(conn.getInputStream))
  }

  def parseHTTPURL(url: String) = parseUrl(url).filter(_.getProtocol == "http")

  println(Try("Rahul").filter(_.equals("Sameer")))

  inputStramForURLFMap("https://www.quora.com/") match {
    case Success(stream) => println(stream.read())
  }


  private def getURLConnect(url: String): Try[Iterator[String]] = {
    for {
      url <- parseUrl(url)
      connection <- Try(url.openConnection())
      is <- Try(connection.getInputStream)
      source = Source.fromInputStream(is)
    } yield source.getLines()
  }

  getURLConnect("https://www.quora.com/") foreach println
}
