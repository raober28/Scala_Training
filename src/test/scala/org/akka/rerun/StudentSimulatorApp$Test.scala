package org.akka.rerun

import akka.actor.ActorSystem
import akka.testkit.{EventFilter, TestActorRef, TestKit}
import com.typesafe.config.ConfigFactory
import org.akka.rerun.university.{TeacherActor, TeacherProtocol}
import TeacherProtocol.QuoteRequest
import org.scalatest.{BeforeAndAfterAll, MustMatchers, WordSpecLike}

/**
  * Created by rahul on 5/7/16.
  */
class TeacherPreTest extends TestKit(ActorSystem("UniversityMessageSystem"))
  with WordSpecLike with MustMatchers with BeforeAndAfterAll {

  //Sends message to the Print Actor. Not even a testcase actually
  "A teacher" must {
    "print a quote when QuoteRequest message is sent" in {
      val teacherRef = TestActorRef[TeacherActor]
      teacherRef  ! QuoteRequest
    }
  }

  //Sends message to the Log Actor. Again, not a testcase per se
  "A teacher with ActorLogging" must {
    "log a quote when a QuoteRequest message is sent" in {
      val teacherRef = TestActorRef[TeacherActor]
      teacherRef ! QuoteRequest
    }
  }

  //Assert the internal state of Log actor.
  "have a quote list of size 4 " in {
    val teacherRef = TestActorRef[TeacherActor]
    teacherRef.underlyingActor.quoteList must have size 4
    teacherRef.underlyingActor.quoteList must have size 4
  }


}

class TeacherTest extends TestKit(ActorSystem("UniversityMessageSystem",
  ConfigFactory.parseString("""akka.loggers = ["akka.testkit.TestEventListener"]""")))
  with WordSpecLike
  with MustMatchers
  with BeforeAndAfterAll {


  //Verifying log messages from eventStream
  "be verifiable via EventFilter in response to a QuoteRequest that is sent" in {
    val teacherRef = TestActorRef[TeacherActor]
    EventFilter.info(pattern = "QuoteResponse*", occurrences = 1) intercept {
      teacherRef ! QuoteRequest
    }
  }

}
