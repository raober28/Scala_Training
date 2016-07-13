package org.akka.rerun.university.requestresponse

import akka.actor.{ActorLogging, Actor, ActorRef}
import Protocol.{ScheduleSignal, QuoteResponse, QuoteRequest, InitSignal}
import scala.concurrent.duration._

/**
  * Created by rahul on 5/7/16.
  */
class StudentActor(teacherRef: ActorRef)  extends Actor with ActorLogging {

  def receive = {
    case InitSignal =>
      teacherRef  ! QuoteRequest

    case ScheduleSignal =>
      import context.dispatcher
      context.system.scheduler.scheduleOnce(5 seconds, teacherRef,QuoteRequest)


    case QuoteResponse(quoteString) =>
      log.info("Received QuoteResponse from Teacher")
      log.info(s"Printing from Student Actor $quoteString")
  }

}
