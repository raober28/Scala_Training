package org.akka.robot

import akka.actor.Actor
import org.akka.robot.ScalaAkkaBot.{Direction, FORWARD}

/**
  * Created by rahul on 23/4/16.
  */

object ScalaAkkaBot {
  sealed abstract class Direction
  case object FORWARD extends Direction
  case object BACKWARDS extends Direction
  case object RIGHT extends Direction
  case object LEFT extends Direction
  case class  Move(direction: Direction)
  case object Stop
  case object GetRobotState
  case class  RobotState(direction: Direction, moving: Boolean)
}

class ScalaAkkaBot extends Actor {
  var moving: Boolean = false
  var direction: Direction = FORWARD

  import ScalaAkkaBot._

  def receive = {
    case Move(newDirection) =>
      val random = scala.util.Random
      moving = true
      direction = newDirection
      println(s"${self.path} am now moving $direction")
      if((random.nextInt(10) % 10 == 0))
        context.stop(self)


    case Stop =>
      moving = false
      println(s"I stopped moving")

    case msg => unhandled(msg)
  }
}