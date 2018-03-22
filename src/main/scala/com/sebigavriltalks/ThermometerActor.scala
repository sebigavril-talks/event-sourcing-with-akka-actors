package com.sebigavriltalks

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Random

import akka.actor.Actor

class ThermometerActor extends Actor {
  private var temperature = 0

  context.system.scheduler.schedule(1.seconds, 1.seconds, self, "tick")

  override def receive: Receive = {
    case "tick"  => temperature = temperature + nextTempIncrement()
    case "read"  => sender() ! temperature
  }

  // (+1 / 0 / -1)
  private def nextTempIncrement() = Random.nextInt(3) - 1
}