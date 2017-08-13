package org.akka.rerun.university.requestresponse

import akka.actor.{Props, ActorSystem}

import Protocol.InitSignal

/**
  * Created by rahul on 5/7/16.
  */
object DriverApp extends App {

  //Initialize the ActorSystem
  val system = ActorSystem("UniversityMessageSystem")

  //construct the teacher actor
  val teacherRef = system.actorOf(Props[TeacherActor], "teacherActor")

  //construct the Student Actor - pass the teacher actorref as a constructor parameter to StudentActor
  val studentRef = system.actorOf(Props(new StudentActor(teacherRef)), "studentActor")

  //send a message to the Student Actor
  studentRef ! InitSignal

  //Let's wait for a couple of seconds before we shut down the system
  Thread.sleep(2000)

  //Shut down the ActorSystem.
  system.terminate()

}

object Protocol {
  case class InitSignal()
  case class ScheduleSignal()
  case class QuoteRequest()
  case class QuoteResponse(quote: String)
}