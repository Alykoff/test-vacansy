package eu.exante.actor

import java.net.InetSocketAddress

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import akka.io.{IO, Tcp}
import akka.util.ByteString
import eu.exante.Main
import eu.exante.data.Tick
import eu.exante.util.AppUtil

class TickerPlantActor(remote: InetSocketAddress) extends Actor with ActorLogging {
  import Tcp._
  import context.{become, system}

  IO(Tcp) ! Connect(remote)

  override def receive: Receive = {
    case CommandFailed(_: Connect) =>
      log.info("connect failed")
      context stop self

    case c @ Connected(remoteInner, local) =>
      log.info("success connect")
      val connection = sender()
      connection ! Register(self)
      become(receiveAfterConnect(connection))
  }

  def receiveAfterConnect(connection: ActorRef): Receive = {
    case data: ByteString =>
      log.warning("Input Byte")
    case CommandFailed(w: Write) =>
      log.warning("Write failed")
    case Received(data) =>
      val tick = Tick.getTick(data)
      log.debug("Tick: " + tick)
      if (!tick.isValid) {
        log.warning("Inconsistent tick data " + tick)
      } else {
        context.actorSelection(AppUtil.getActorPath(Main.storageActor)) ! tick
      }
    case _: ConnectionClosed =>
      log.info("Connection close")
      become(receive)
  }
}
