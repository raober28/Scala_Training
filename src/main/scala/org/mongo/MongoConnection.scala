package org.mongo

/**
  * Created by rahul on 22/7/16.
  */

import com.mongodb.casbah.Imports._
import javax.net.ssl._

object MongoConnection extends App {



  val server = new ServerAddress("localhost", 27017)

  //val credentials = MongoCredential.createMongoCRCredential("heroku", "simn-eu-dedicated", "mu8ro5xy5gyrh0bo3kit".toCharArray)

  val options = MongoClientOptions(socketFactory = SSLSocketFactory.getDefault)

  //val mongoClient = MongoClient(server, List(credentials), options)

  val mongoClient = MongoClient(server)


  val db = mongoClient("simn")

  val devices = db("devices")

  println(db.getStats())

  println(devices.count())




  //println(mongoDatabase("devices").count())
}


