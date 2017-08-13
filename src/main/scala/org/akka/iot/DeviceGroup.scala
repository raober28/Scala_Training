package org.akka.iot

import akka.actor.{Actor, ActorLogging, ActorRef, Props, Terminated}
import org.akka.iot.DeviceGroup.{ReplyDeviceList, RequestAllTemperatures, RequestDeviceList}
import org.akka.iot.DeviceManager.RequestTrackDevice

import scala.concurrent.duration.DurationLong

object DeviceGroup {
  def props(groupId: String): Props = Props(new DeviceGroup(groupId))

  sealed trait TemperatureReading

  final case class RequestDeviceList(requestId: Long)

  final case class ReplyDeviceList(requestId: Long, ids: Set[String])

  final case class RequestAllTemperatures(requestId: Long)

  final case class RespondAllTemperatures(requestId: Long, temperatures: Map[String, TemperatureReading])

  final case class Temperature(value: Double) extends TemperatureReading

  final case object TemperatureNotAvailable extends TemperatureReading

  final case object DeviceNotAvailable extends TemperatureReading

  final case object DeviceTimedOut extends TemperatureReading

}

class DeviceGroup(groupId: String) extends Actor with ActorLogging {
  var deviceIdToActor = Map.empty[String, ActorRef]
  var actorToDeviceId = Map.empty[ActorRef, String]

  override def preStart(): Unit = log.info("DeviceGroup {} started", groupId)

  override def postStop(): Unit = log.info("DeviceGroup {} stopped", groupId)

  override def receive: Receive = {
    case trackMsg@RequestTrackDevice(`groupId`, _) =>
      deviceIdToActor.get(trackMsg.deviceId) match {
        case Some(deviceToActor) =>
          deviceToActor forward trackMsg
        case None =>
          log.info("Creating device actor for {}", trackMsg.deviceId)
          val deviceToActor = context.actorOf(Device.props(groupId, trackMsg.deviceId), s"device-${trackMsg.deviceId}")
          context.watch(deviceToActor)
          actorToDeviceId += deviceToActor -> trackMsg.deviceId
          deviceIdToActor += trackMsg.deviceId -> deviceToActor
          deviceToActor forward trackMsg
      }

    case RequestTrackDevice(groupId, deviceId) =>
      log.warning(
        "Ignoring TrackDevice request for {}. This actor is responsible for {}.",
        groupId, this.groupId
      )

    case RequestDeviceList(requestId) =>
      sender() ! ReplyDeviceList(requestId, deviceIdToActor.keySet)

    case RequestAllTemperatures(requestId) =>
      context.actorOf(DeviceGroupQuery.props(
        actorToDeviceId,
        requestId = requestId,
        requester = sender(),
        timeout = 3.seconds))

    case Terminated(deviceActor) =>
      val deviceId = actorToDeviceId(deviceActor)
      log.info("Device actor for {} has been terminated", deviceId)
      actorToDeviceId -= deviceActor
      deviceIdToActor -= deviceId
  }
}
