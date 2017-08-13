package org.akka.rerun.university

import akka.actor.{ActorRef, ActorSystem, Props}

/**
  * Created by rahul on 1/7/16.
  */
object StudentSimulatorApp extends App {
  import TeacherProtocol.QuoteRequest

  //Initialize the ActorSystem
  val actorSystem = ActorSystem("UniversityMessageSystem")

  //construct the Teacher Actor Ref
  val teacherActorRef: ActorRef = actorSystem.actorOf(Props[TeacherActor])

  //send a message to the Teacher Actor
  teacherActorRef ! QuoteRequest

  //Let's wait for a couple of seconds before we shut down the system
  Thread.sleep(2000)

  //Shutdown the ActorSystem.
  actorSystem.terminate()
}

object TeacherProtocol {
  case class QuoteRequest()
  case class QuoteResponse(quoteString: String)

}
