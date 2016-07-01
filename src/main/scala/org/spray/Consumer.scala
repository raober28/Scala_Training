
package org.spray


import spray.client.pipelining._
import spray.http._
import spray.json._

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}


/**
  * Created by rahul on 29/6/16.
  *
  **/

object Consumer {

  def getSongs() = {

    import SongProtocol._

    import concurrent.ExecutionContext.Implicits.global
    implicit val songJsonFormat = jsonFormat4(Song)

    val apiLocation = "http://192.168.1.254:8080"
    val timeout = 5.seconds
    val pipeline: HttpRequest => Future[List[Song]] = sendReceive ~> unmarshal[List[Song]]
    val f: Future[List[Song]] = pipeline(Get(s"$apiLocation/songs"))
    val songs = Await.result(f, timeout)
    println(s"Got this list of $songs")

  }
}




object SongProtocol extends DefaultJsonProtocol {

  case class Song(id: String, name: String, songUrl: String, picUrl: String)

  implicit val songJsonFormat = jsonFormat4(Song)
}


