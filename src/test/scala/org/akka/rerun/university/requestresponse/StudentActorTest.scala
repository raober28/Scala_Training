package org.akka.rerun.university.requestresponse

import akka.actor.{ActorSystem, Props}
import akka.testkit.{EventFilter, TestKit}
import com.typesafe.config.ConfigFactory
import org.akka.rerun.university.requestresponse.Protocol.{ScheduleSignal, InitSignal}
import org.scalatest.{BeforeAndAfterAll, MustMatchers, WordSpecLike}

/**
  * Created by rahul on 5/7/16.
  */
class StudentActorTest extends TestKit(ActorSystem("UniversityMessageSystem",
  ConfigFactory.parseString(
    """akka {
        |loggers = ["akka.testkit.TestEventListener"]
        |test = {
        | filter-leeway = 7s
        |}
      |}""".stripMargin)))
  with WordSpecLike
  with MustMatchers
  with BeforeAndAfterAll {

  "A student" must {
    "log a QuoteResponse eventually when an InitSignal is sent to it" in {

      val teacherRef = system.actorOf(Props[TeacherActor], "teacher-actor")
      val studentRef = system.actorOf(Props(new StudentActor(teacherRef)), "student-actor")

      EventFilter.info (start = "Printing from Student Actor", occurrences=1).intercept {
        studentRef  ! InitSignal
      }

    }

    "A delayed student" must {
      "fire the QuoteRequest after 5 seconds when an ScheduleSignal is sent to it" in {

        val teacherRef = system.actorOf(Props[TeacherActor], "teacherActorDelayed")
        val studentRef =  system.actorOf(Props(new StudentActor(teacherRef)), "studentDelayedActor")

        EventFilter.info(start="Printing from Student Actor", occurrences=1).intercept {
          studentRef  ! ScheduleSignal
        }
      }
    }
  }

}
