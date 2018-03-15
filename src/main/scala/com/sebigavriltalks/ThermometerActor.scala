package com.sebigavriltalks

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Random

import akka.actor.Actor
import com.sebigavriltalks.ThermometerActorProtocol.Tick
import com.sebigavriltalks.ThermometerReader.{Read, Reading}

class ThermometerActor extends Actor {

  private var temperature = 0

  context.system.scheduler.schedule(1.seconds, 1.seconds, self, Tick)

  override def receive: Receive = {
    case Tick  => temperature = temperature + Random.nextInt(3) - 1 // 1, 0, -1
    case Read  => sender() ! Reading(temperature)
  }

  // (+1 / 0 / -1)
  private def nextTempIncrement() = Random.nextInt(3) - 1
}

object ThermometerActorProtocol {

  case object Tick

}