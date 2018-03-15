name := "event-sourcing-with-akka-actors"

scalaVersion := "2.12.2"

lazy val akkaVersion = "2.5.11"

libraryDependencies ++= Seq(
  "com.typesafe.akka"           %% "akka-actor"       % akkaVersion,
  "com.typesafe.akka"           %% "akka-persistence" % akkaVersion,
  "org.fusesource.leveldbjni"   %  "leveldbjni-all"   % "1.8"
)
