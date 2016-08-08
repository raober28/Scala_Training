package org.akka.robot

import akka.actor.{Props, ActorSystem}


/**
  * Created by rahul on 23/4/16.
  */
object ScalaBotMain extends App {
  // Create the 'helloakka' actor system
  val system = ActorSystem("helloakka")
  // Create the 'akkaBot' actor
  //val akkaBot = system.actorOf(Props[ScalaAkkaBot], "akkaBot")
  val akkaBotMaster = system.actorOf(Props[ScalaBotMaster], "scalaBotMaster")
  println("ScalaBotMain Actor System was created")

  /*akkaBot ! ScalaAkkaBot.Move(ScalaAkkaBot.FORWARD)
  akkaBot ! ScalaAkkaBot.Move(ScalaAkkaBot.BACKWARDS)
  akkaBot ! ScalaAkkaBot.Stop*/

  akkaBotMaster ! ScalaBotMaster.StartChildBots

}
