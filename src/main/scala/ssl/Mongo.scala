package ssl

/**
  * Created by rahul on 12/4/16.
  *
  *
  */

import java.net.ServerSocket
import javax.net.ssl._

import com.mongodb.casbah.Imports._


object Mongo {

    def main(args: Array[String]) = {
      connect()
    }
    def connect() = {
      System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2")
      System.setProperty("https.cipherSuites", "ECDHE-RSA-AES256-GCM-SHA384")
      java.lang.System.setProperty("jdk.tls.client.protocols", "TLSv1,TLSv1.1,TLSv1.2");
      println("property: " + System.getProperty("https.cipherSuites"))

     /* val configure = new ssl.Configure()
      configure.enable_TLS("aws-us-east-1-portal.12.dblayer.com", "10661")*/

      /*val context: SSLContext = SSLContext.getInstance("TLS")
      context.init(null, null, null)*/

     // val factory = SSLSocketFactory.getDefault().asInstanceOf[SSLSocketFactory]

      /*System.setProperty("https.protocols", "TLSv1.2")
      System.setProperty("https.cipherSuites", "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256")

      println("my property: " + System.getProperty("https.protocols"))

*/

      /*val ssl : SSLSocketFactory = new SSLSocketFactoryImpl();
      val scoket1 : Socket  = new SSLSocket();
      val socket = ssl.createSocket("aws-us-east-1-portal.12.dblayer.com", 10661);

      val sslSocket = socket.asInstanceOf[SSLSocket];

      sslSocket.setEnabledProtocols(Array[String]("TLSv1", "SSLv3"));
      System.out.println(sslSocket.getEnabledCipherSuites());*/
      /*sslSocket.setEnabledCipherSuites(Array[String]("ECDHE-RSA-AES256-GCM-SHA384"))*/


      /*val prop = SSLSocketFactory.getDefault();*/

      new ServerSocket(6559)

      java.lang.System.setProperty(
        "jdk.tls.client.protocols", "TLSv1,TLSv1.1,TLSv1.2")


      val mongoOptions: MongoClientOptions =MongoClientOptions(autoConnectRetry = true, socketFactory =SSLSocketFactory.getDefault())
      val credentials = List(MongoCredential.createCredential("heroku", "simn", "mu8ro5xy5gyrh0bo3kit".toCharArray))
      val mongoClient = MongoClient(new ServerAddress("aws-us-east-1-portal.12.dblayer.com", 10661), credentials, mongoOptions)
      val db = mongoClient("simn")
      val devices = db("devices")


      println("count : " + devices.count())


    }

}
