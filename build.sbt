name := "Scala_Training"

version := "1.0"

scalaVersion := "2.11.7"


libraryDependencies ++= {
  val akkaV = "2.3.9"
  val sprayV = "1.3.3"
  Seq(
    "io.spray"            %%  "spray-can"       % sprayV,
    "io.spray"            %%  "spray-routing"   % sprayV,
    "io.spray"            %%  "spray-testkit"   % sprayV    % "test",
    "io.spray"            %%  "spray-json"      % "1.3.2",
    "io.spray"            %%  "spray-client"    % sprayV,
    "com.typesafe.akka"   %%  "akka-actor"      % akkaV,
    "com.typesafe.akka"   %%  "akka-slf4j"      % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"    % akkaV     % "test",
    "org.specs2"          %%  "specs2-core"     % "2.3.11"  % "test",
    "org.mongodb"         %%  "casbah"          % "2.8.2",
    "org.scalatest"       %%  "scalatest"       % "2.2.6"   % "test",
    "ch.qos.logback"       %  "logback-classic" % "1.1.2"   % "runtime"
  )
}
    