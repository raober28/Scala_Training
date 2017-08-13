package org.akka.countWords

import akka.util.Timeout
import scala.concurrent.duration._
import akka.pattern.ask
import akka.dispatch.ExecutionContexts._
import akka.actor.{Props, ActorSystem}


/**
  * Created by rahul on 30/3/16.
  */
object Sample {

  implicit val ec = global

  def main(args: Array[String]) {

    countWordsWithActors()

  }


  def countWordsWithActors() = {
    def now = System.nanoTime()
    val before = now

    val system = ActorSystem("System")
    val actor = system.actorOf(Props(new WordCounterActor("reports/WDI_Data.csv")))
    implicit val timeout = Timeout(1000 seconds)

    val future = actor ? StartProcessFileMsg()
    future.map { result =>
      println("Total number of words " + result)
      system.terminate
      val time = now - before
      println(s"Total time took (${time.toDouble / (1000 * 1000 * 1000)}) seconds")

    }
  }


  def countWords() = {

    def now = System.nanoTime()
    val before = now

    val file = scala.io.Source.fromFile("reports/WDI_Data.csv")
    val lines = file.getLines()

    var length = 0

    for (line <- lines)
      length += line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)").length

    val time = now - before
    println(s"Total time took (${time.toDouble / (1000 * 1000 * 1000)}) seconds")

    length
  }

}
