package org.parser

/**
  * Created by rahul on 22/4/17.
  */
object Locality extends App {

  case class Location(lat: Double, long: Double)
  case class Resident(name : String, age: Int, role: Option[String])
  case class Place(name: String, location: Location, residents : Seq[Resident])

  import play.api.libs.json._
  import play.api.libs.json.Reads._
  import play.api.libs.functional.syntax._

  implicit val locationWrites =  new Writes[Location] {
    def writes(location: Location) = Json.obj(
      "lat" -> location.lat,
      "long" -> location.long
    )
  }

  implicit val residentWrites = new Writes[Resident] {
    def writes(resident: Resident) = Json.obj(
      "name" -> resident.name,
      "age" -> resident.age,
      "role" -> resident.role
    )
  }

  implicit val placeWrites = new Writes[Place] {
    def writes(place: Place) = Json.obj(
      "name" -> place.name,
      "location" -> place.location,
      "residents" -> place.residents
    )
  }


  val latPath = __ \ "location" \ "lat"

  val namesPath = JsPath \\ "name"


  val firstResidentPath = (JsPath \ "residents")(0)

  val namesReads : Reads[String] = (JsPath \ "name").read[String]

  val locationReadsBuilder =
    (JsPath \ "lat").read[Double] and
    (JsPath \ "long").read[Double]

  implicit val locationReads: Reads[Location] = (
    (JsPath \ "lat").read[Double](min(-90.0) keepAnd max(90.0)) and
      (JsPath \ "long").read[Double](min(180.0) keepAnd max(180.0))
  )(Location.apply _)


  implicit val residentReads : Reads[Resident] = (
    (JsPath \ "name").read[String](minLength[String](2)) and
    (JsPath \ "age").read[Int](min(0) keepAnd max(150)) and
    (JsPath \ "role").readNullable[String]
  )(Resident.apply _)

  implicit val placeReads : Reads[Place] = (
    (JsPath \ "name").read[String](minLength[String](2)) and
    (JsPath \ "location").read[Location] and
    (JsPath \ "residents").read[Seq[Resident]]
  )(Place.apply _)

  json.validate[Place] match {
    case s: JsSuccess[Place] => {
      val place: Place = s.get
    }
    case e: JsError =>
  }



  val namesResult : JsResult[String] = json.validate[String](namesReads)

  namesResult match {
    case s: JsSuccess[String] => println("Name : " + s.get)
    case e: JsError => println("Errors: " + JsError.toJson(e).toString())
  }




  val place = Place(
    "Watership Down",
    Location(51.235685, -1.309197),
    Seq(
      Resident("Fiver", 4, None),
      Resident("Bigwig", 6, Some("Owsla"))
    )
  )

  val json = Json.toJson(place)

  println(Json.prettyPrint(json))

}
