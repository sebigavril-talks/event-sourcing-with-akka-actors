package com.sebigavriltalks

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Random

import akka.persistence.PersistentActor

class ThermometerActor(id: String) extends PersistentActor {
  private var temperature = 0

  context.system.scheduler.schedule(1.seconds, 1.seconds, self, "tick")

  override def persistenceId: String = id

  override def receiveCommand: Receive = {
    case "read"  => sender() ! temperature
    case "tick"  => persist(nextTempIncrement()) {
      increment => updateState(increment)
    }
  }

  override def receiveRecover: Receive = {
    case increment: Int =>
      println(s"Recovering with $increment")
      updateState(increment)
  }

  private def updateState(event: Any) = event match {
    case increment: Int => temperature = temperature + increment
  }

  // (+1 / 0 / -1)
  private def nextTempIncrement() = Random.nextInt(3) - 1
}