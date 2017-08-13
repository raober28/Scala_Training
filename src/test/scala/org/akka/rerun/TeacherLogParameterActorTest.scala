package org.akka.rerun

import akka.actor.ActorSystem
import akka.testkit.{EventFilter, TestActorRef, TestKit}
import com.typesafe.config.ConfigFactory
import org.akka.rerun.university.{TeacherLogParameterActor, TeacherProtocol}
import TeacherProtocol.QuoteRequest
import org.scalatest.{BeforeAndAfterAll, MustMatchers, WordSpecLike, FunSuite}

/**
  * Created by rahul on 5/7/16.
  */
class TeacherLogParameterActorTest extends TestKit(ActorSystem("UniversityMessageSystem",
  ConfigFactory.parseString("""akka.loggers = ["akka.testkit.TestEventListener"]""")))
  with WordSpecLike
  with MustMatchers
  with BeforeAndAfterAll {

  //have a quote list of the same size as the input parameter
    "have a quote list of the same size as the input parameter" in {
      val quotes = List(
        "Moderation is for cowards",
        "Anything worth doing is worth overdoing",
        "The trouble is you think you have time",
        "You never gonna know if you never even try")

      val teacherRef = TestActorRef(new TeacherLogParameterActor(quotes))

      teacherRef.underlyingActor.quoteList must have size 4
      EventFilter.info(pattern = "QuoteResponse*", occurrences = 1) intercept {
        teacherRef ! QuoteRequest
      }
    }

  override def afterAll(): Unit = {
    super.afterAll()
    system.terminate()
  }

}
