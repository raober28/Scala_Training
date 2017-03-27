package org.mongo

/**
  * Created by rahul on 22/7/16.
  */

import java.text.{DateFormat, SimpleDateFormat}
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.{Date, TimeZone}
import javax.net.ssl._

import com.mongodb.casbah.Imports._

object MongoConnection extends App {
  val server = new ServerAddress("aws-us-east-1-portal.12.dblayer.com", 10661)
  val credentials = MongoCredential.createCredential("heroku", "simn", "mu8ro5xy5gyrh0bo3kit".toCharArray)
  val options = MongoClientOptions(socketFactory = SSLSocketFactory.getDefault)
  val mongoClient = MongoClient(List(server), List(credentials), options)

  val db = mongoClient("simn")
  val incidents = db("incidents")

  val latestIncidents = incidents.find().sort(MongoDBObject("_id" -> -1)).limit(60)

  println("%20s %20s %20s %20s %20s %20s".format("Berger", "Tijdstip melding", "Dossier", "Meldnummer",
    "Tijdstip status", "Status"))

  var berger = ""
  var meldingtijd: AnyRef = ""
  var companyid = ""
  var dossier = ""
  var meldnummer = ""
  var statustijd = ""
  var status = ""

  for (incident <- latestIncidents) {
    if (incident.get("companyId") == "13867") berger = "Barendregt"
    else if (incident.get("companyId") == "73423") berger = "Van de Graaf"
    else if (incident.get("companyId") == "37629") berger = "Quartel"
    else if (incident.get("companyId") == "83144") berger = "Wolves"
    else if (incident.get("companyId") == "41327") berger = "Houterman"
    else if (incident.get("companyId") == "77327") berger = "De Heer"
    else if (incident.get("companyId") == "52657") berger = "BCB"
    else if (incident.get("companyId") == "97584") berger = "Logicx"
    else if (incident.get("companyId") == "48271") berger = "De Jonge"
    else if (incident.get("companyId") == "25819") berger = "Van Wijngaarden"
    else if (incident.get("companyId") == "89375") berger = "Hoogwout"
    else if (incident.get("companyId") == "11109") berger = "Leerentveld"
    else if (incident.get("companyId") == "68275") berger = "Van Eijck"
    else if (incident.get("companyId") == "58279") berger = "Autax"
    else if (incident.get("companyId") == "30509") berger = "De Moor"
    else if (incident.get("companyId") == "33771") berger = "Heiltjes"
    else if (incident.get("companyId") == "59111") berger = "Hof"
    else if (incident.get("companyId") == "57866") berger = "Poort"
    else if (incident.get("companyId") == "79288") berger = "Broekmans"
    else if (incident.get("companyId") == "31789") berger = "Van Amerongen"
    else if (incident.get("companyId") == "28356") berger = "Dallinga"
    else if (incident.get("companyId") == "52437") berger = "Collewijn Nuis"
    else if (incident.get("companyId") == "92511") berger = "Twente"
    else if (incident.get("companyId") == "89715") berger = "Delta"
    else
      berger = "Test"

    val inputFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy")

    var str = incident.get("timestamp").toString
    var parsed: ZonedDateTime = ZonedDateTime.parse(str, inputFormat)

    val output: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    meldingtijd = timeZoneConvertor(incident.get("timestamp").toString,"EEE MMM dd HH:mm:ss zzz yyyy","yyyy-MM-dd HH:mm:ss", "GMT" )
    companyid = incident.get("companyId").toString
    dossier = incident.get("id").toString
    meldnummer = incident.get("orderId").toString
    str = incident.get("status").asInstanceOf[DBObject].get("time").toString
    parsed = ZonedDateTime.parse(str, inputFormat)
    statustijd = output.format(parsed)
    status = incident.get("status").asInstanceOf[DBObject].get("name").toString

    println("%20s %20s %20s %20s %20s %20s".format(berger, meldingtijd, dossier, meldnummer, statustijd, status))
  }


  def timeZoneConvertor(srcDate: String, srcDateFormatStr: String, destDateFormatStr: String,
                        destTimeZone: String) = {

    var destDate: String = null

    try {
      val srcDateFormat: DateFormat = new SimpleDateFormat(srcDateFormatStr)
      srcDateFormat.setTimeZone(TimeZone.getDefault)
      val destDateFormat: DateFormat = new SimpleDateFormat(destDateFormatStr)
      destDateFormat.setTimeZone(TimeZone.getTimeZone(destTimeZone))

      val date: Date = srcDateFormat.parse(srcDate)
      destDate = destDateFormat.format(date)

    } catch {
      case e: Throwable => println(e.getMessage)
    }
    destDate
  }

}


