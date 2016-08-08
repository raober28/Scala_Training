package org.akka.robot

import akka.actor.{ActorRef, ActorSystem, Props, Actor}
import org.akka.robot.ScalaAkkaBot.{Move, FORWARD}
import org.akka.robot.ScalaBotMaster.StartChildBots

/**
  * Created by rahul on 24/4/16.
  */

object ScalaBotMaster {
  case object StartChildBots
}

class ScalaBotMaster extends Actor {

  for(index <- 1 to 10) {
    context.actorOf(Props[ScalaAkkaBot], s"$index")
  }

  def receive = {
    case StartChildBots =>
      context.children.foreach { child =>
        println(s"child=$child")
        child ! Move(FORWARD)
      }
      println("Master has started children bots.")
  }

}



