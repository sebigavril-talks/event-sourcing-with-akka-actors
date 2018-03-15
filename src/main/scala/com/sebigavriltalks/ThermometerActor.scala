package com.sebigavriltalks

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Random

import akka.persistence.PersistentActor
import com.sebigavriltalks.ThermometerActorProtocol.{Event, Tick, TickEvent}
import com.sebigavriltalks.ThermometerReader.{Read, Reading}

class ThermometerActor(id: String) extends PersistentActor {

  private var temperature = 0

  context.system.scheduler.schedule(1.seconds, 1.seconds, self, Tick)

  override def persistenceId: String = id

  override def receiveCommand: Receive = {
    case Tick  => persist(TickEvent(nextTempIncrement())) { evt => updateState(evt) }
    case Read  => sender() ! Reading(temperature)
  }

  override def receiveRecover: Receive = {
    case event: Event =>
      println(s"Recovering with $event")
      updateState(event)
  }

  private def updateState(event: Event) = event match {
    case TickEvent(inc) => temperature = temperature + inc
  }

  // (+1 / 0 / -1)
  private def nextTempIncrement() = Random.nextInt(3) - 1
}

object ThermometerActorProtocol {

  sealed trait Command
  case object Tick extends Command

  sealed trait Event
  case class TickEvent(inc: Int) extends Event

}