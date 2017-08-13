package org.akka.iot

import akka.actor.{Actor, ActorLogging, Props}

/**
  * Created by rahul on 7/8/17.
  */

object IotSupervisor {
  def props(): Props = Props(new IotSupervisor)
}

class IotSupervisor extends Actor with ActorLogging {
  override def preStart(): Unit = log.info("IoT application started")

  override def postStop(): Unit = log.info("IoT application stopped")

  //No need to handle any messages
  override def receive: Receive = Actor.emptyBehavior

}


