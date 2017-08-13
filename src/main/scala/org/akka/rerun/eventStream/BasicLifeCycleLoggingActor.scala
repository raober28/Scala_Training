package org.akka.rerun.eventStream

import akka.actor.SupervisorStrategy.Stop
import akka.actor._
import akka.event.LoggingReceive
import com.typesafe.config.ConfigFactory

/**
  * Created by rahul on 6/7/16.
  */
class BasicLifeCycleLoggingActor extends Actor with ActorLogging {

  def receive = LoggingReceive {
    case "hello" => log.info("hello")
    case "stop"  => context.stop(self)
  }
}

object LifeCycleApp extends App {
  val actorSystem = ActorSystem("LifeCycleActorSystem")
  val lifecycleActor = actorSystem.actorOf(Props[BasicLifeCycleLoggingActor], "lifecycleActor")

  val deadLetterListener  = actorSystem.actorOf(Props[MyCustomDeadLetterListener], "mycustomdeadlistener")
  actorSystem.eventStream.subscribe(deadLetterListener, classOf[DeadLetter])




  lifecycleActor  ! "hello"
  lifecycleActor  ! "stop"
  lifecycleActor  ! "hello"

  Thread.sleep(2000)
  actorSystem.terminate()
}


class MyCustomDeadLetterListener extends Actor {
  def receive = {
    case deadLetter: DeadLetter => println(s"FROM CUSTOM LISTENER $deadLetter")
  }
}
