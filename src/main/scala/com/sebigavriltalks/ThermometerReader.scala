package com.sebigavriltalks

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

import akka.actor.{ ActorRef, Actor }

class ThermometerReader(thermometer: ActorRef) extends Actor {

  context.system.scheduler.schedule(1.seconds, 1.seconds, thermometer, "read")

  override def receive: Receive = {
    case temperature: Int => println(s"Current reading: $temperature")
  }
}
