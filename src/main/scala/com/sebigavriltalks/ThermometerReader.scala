package com.sebigavriltalks

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

import akka.actor.{ ActorRef, Actor }
import com.sebigavriltalks.ThermometerReader._

class ThermometerReader(thermometer: ActorRef) extends Actor {

  context.system.scheduler.schedule(1.seconds, 1.seconds, thermometer, Read)

  override def receive: Receive = {
    case Reading(t) => println(s"Current reading: $t")
  }
}

object ThermometerReader {
  case object Read
  case class Reading(temperature: Int)
}
