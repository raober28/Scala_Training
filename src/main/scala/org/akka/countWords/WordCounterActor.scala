package org.akka.countWords

import akka.actor.{Props, ActorRef, Actor}
import scala.io.Source._
import akka.actor.OneForOneStrategy
import akka.actor.SupervisorStrategy._
import scala.concurrent.duration._

/**
  * Created by rahul on 30/3/16.
  */

case class StartProcessFileMsg()

class WordCounterActor(fileName: String) extends Actor {

  private var running = false
  private var totalLines = 0
  private var linesProcessed = 0
  private var result = 0
  private var fileSender: Option[ActorRef] = None

  def receive = {
    case StartProcessFileMsg() => {
      if(running) {
        println("Warning: duplicate start message received")
      } else {
        running = true
        fileSender = Some(sender)
        fromFile(fileName).getLines.foreach { line =>
          context.actorOf(Props[StringCounterActor]) ! ProcessStringMsg(line)
          totalLines += 1

        }
      }
    }

    case StringProcessedMsg(words) => {
      result += words
      linesProcessed += 1
      if(linesProcessed == totalLines) {
        fileSender.map( _ ! result)
      }
    }

    case  _ => println("message not recognized")
  }


  override val supervisorStrategy =
    OneForOneStrategy() {
      case _: ArithmeticException => Resume
      case _: NullPointerException => Restart
      case _: IllegalArgumentException => Stop
      case _: Exception => Escalate
    }

}
