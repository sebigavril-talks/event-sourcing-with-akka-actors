package com.sebigavriltalks

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Random

import akka.actor.Actor

class ThermometerActor extends Actor {
  override def receive: Receive = Actor.emptyBehavior

  // (+1 / 0 / -1)
  private def nextTempIncrement() = Random.nextInt(3) - 1
}