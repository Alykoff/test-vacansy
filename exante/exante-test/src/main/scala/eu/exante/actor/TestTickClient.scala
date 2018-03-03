package eu.exante.actor

import java.net.InetSocketAddress
import java.nio.charset.StandardCharsets

import akka.actor.{Actor, ActorRef, Props}
import akka.event.Logging
import akka.io.{IO, Tcp}
import akka.util.ByteString

class TestTickClient(remote: InetSocketAddress) extends Actor {
  import Tcp._
  import context.{become, system}

  val log = Logging(context.system, this)

  IO(Tcp) ! Connect(remote)

  def receive = {
    case CommandFailed(_: Connect) =>
      log.info("connect failed")
      context stop self

    case c @ Connected(remoteInner, local) =>
      log.info("success connect")
      val connection = sender()
      connection ! Register(self)
      become(receiveAfterConnect(connection))
  }

  def receiveAfterConnect(connection: ActorRef): Actor.Receive = {
    case data: ByteString =>
      log.info("data: ")
      log.info(data.decodeString(StandardCharsets.UTF_8.name()))
    case CommandFailed(w: Write) =>
      log.info("write failed")
    case Received(data) =>
      log.info(data.decodeString(StandardCharsets.UTF_8.name()))
    case _: ConnectionClosed =>
      log.info("connection close")
      context stop self
  }
}
