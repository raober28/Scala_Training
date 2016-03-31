package org.akka.countWords

import akka.actor.Actor

/**
  * Created by rahul on 30/3/16.
  */

case class ProcessStringMsg(string: String)
case class StringProcessedMsg(words: Integer)

class StringCounterActor extends Actor {

  def receive = {

    case ProcessStringMsg(string) => {
      val wordsInLine = string.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)").length

      sender ! StringProcessedMsg(wordsInLine)
    }


    case _ => println("Error: message not recognized")
  }
}
