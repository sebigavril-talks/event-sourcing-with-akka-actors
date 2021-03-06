package com.sebigavriltalks

import akka.actor.{ ActorSystem, Props }

object Demo extends App {

  val system = ActorSystem("smart-home-system")

  val thermometerActor = system.actorOf(Props(new ThermometerActor("1")))
  val readerActor = system.actorOf(Props(new ThermometerReader(thermometerActor)))
}
