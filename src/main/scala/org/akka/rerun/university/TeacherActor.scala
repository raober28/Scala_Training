package org.akka.rerun.university

import akka.actor.{Actor, ActorLogging}
import org.akka.rerun.university.TeacherProtocol.{QuoteRequest, QuoteResponse}

/**
  * Your Teacher Actor Class
  *
  * The class could use refinement by way of
  * using ActorLogging which uses the EventBus of the Actor framework
  * instead of the plan old System out
  */
class TeacherActor extends Actor with ActorLogging {

  val quotes = List(
    "Moderation is for cowards",
    "Anything worth doing is worth overdoing",
    "The trouble is you think you have time",
    "You never gonna know if you never try"
  )

  def receive = {
    case QuoteRequest =>
      import scala.util.Random

      //Get a random quote from the list and construct a response
      val quoteResponse = QuoteResponse(quotes(Random.nextInt(quotes.size)))
      log.info(quoteResponse.toString)
  }

  def quoteList = quotes

}



class TeacherLogParameterActor(quotes: List[String]) extends Actor with ActorLogging {

  def receive = {
    case QuoteRequest =>
      import scala.util.Random

      //Get a random quote from the list and construct a response
      val quoteResponse = QuoteResponse(quotes(Random.nextInt(quotes.size)))
      log.info(quoteResponse.toString)
  }

  def quoteList = quotes

}
