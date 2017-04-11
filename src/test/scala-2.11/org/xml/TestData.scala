package org.xml

/**
  * Created by rahul on 28/3/17.
  */
object TestData {

  def incident(reporter: Any, reporterPhone: Int, reporterCaseNo: Any, incidentTimeStamp: Int, incidentType: Any, roadNo: Int, hectometer: Double, hectometerValue: Any, lane: Any): Any = {
    <?xml version="1.0" encoding="utf-8"?>
      <incident>
        <alarmcentrale>{reporter}</alarmcentrale>
        <telefoonac>{reporterPhone}</telefoonac>
        <dossiernummerac>{reporterCaseNo}</dossiernummerac>
        <tijdstipmeldingac>{incidentTimeStamp}</tijdstipmeldingac>
        <incidenttype>{incidentType}</incidenttype>
        <opmerkingenac>Lekke band</opmerkingenac>
        <incidentlocatie>
          <wegnummer>{roadNo}</wegnummer>
          <hectometer>{hectometer}</hectometer>
          <hectometerletter>{hectometerValue}</hectometerletter>
          <rijbaan>{lane}</rijbaan>
          <straat/>
          <huisnummer/>
          <huisnummertoevoeging/>
          <plaats/>
          <provincie/>
          <land/>
          <lat/>
          <lon/>
        </incidentlocatie>
        <voertuig>
          <kenteken>WW-45-DB</kenteken>
        </voertuig>
        <polisgegevens>
          <berijder>Jansen</berijder>
          <telefoonberijder>0645345235</telefoonberijder>
          <verzekeraar/>
          <polisdekking/>
        </polisgegevens>
        <lcm>
          <telefoonlcm/>
          <lcmdossier/>
          <lcmberger/>
          <tijdstipopdrachtlcm/>
          <opmerkingenlcm/>
          <lcmberger/>
          <telefoonlcmberger/>
        </lcm>
      </incident>

  }

}
