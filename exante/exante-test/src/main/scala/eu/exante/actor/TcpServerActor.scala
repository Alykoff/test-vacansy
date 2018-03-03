package eu.exante.actor

import java.net.InetSocketAddress

import akka.actor.{Actor, ActorLogging, Props}
import akka.io.Tcp.{CommandFailed, Register}
import akka.io.{IO, Tcp}
import eu.exante.Main
import eu.exante.actor.ServerHandlerActor.NewClient
import eu.exante.util.{AppUtil, TimeUtil}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

class TcpServerActor(socketAddress: InetSocketAddress) extends Actor with ActorLogging{
  import context.system

  IO(Tcp) ! Tcp.Bind(self, socketAddress)

  def receive = {
    case b @ Tcp.Bound(localAddress) =>
      log.debug("Spawned: " + localAddress.toString)

    case CommandFailed(_: Tcp.Bind) =>
      log.warning("Command failed. Going to stop server.")
      context stop self

    case c @ Tcp.Connected(remote, local) =>
      log.info("Connected: " + remote.toString)
      val client = sender()
      context.actorSelection(AppUtil.getActorPath(Main.serverHandlerNameActor))
        .resolveOne()(TimeUtil.timeoutForAsk)
        .map { handler =>
          client ! Register(handler)
          handler ! NewClient(client)
        }.onComplete{
          case Failure(e) => log.error("error: " + e.getMessage)
          case Success(_) =>
        }
  }
}
