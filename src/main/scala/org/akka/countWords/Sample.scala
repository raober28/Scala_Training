package org.akka.countWords

import akka.util.Timeout
import scala.concurrent.duration._
import akka.pattern.ask
import akka.dispatch.ExecutionContexts._
import akka.actor.{Props, ActorSystem}


/**
  * Created by rahul on 30/3/16.
  */
object Sample{

  implicit val ec = global

  def main(args: Array[String]) {
    def now = System.nanoTime()
    val before = now
    val system = ActorSystem("System")
    val actor = system.actorOf(Props(new WordCounterActor("all-bible.txt")))
    implicit val timeout = Timeout(25 seconds)
    val future = actor ? StartProcessFileMsg()
    future.map { result =>
      println("Total number of words " + result)
      val time = now - before
      println(s"Total time took (${time.toDouble / (1000 * 1000)})")
      system.shutdown
    }
  }

}
