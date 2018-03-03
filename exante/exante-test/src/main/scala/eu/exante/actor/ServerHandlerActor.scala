package eu.exante.actor

import java.time.LocalDateTime

import akka.actor.{Actor, ActorLogging, ActorRef}
import akka.io.Tcp
import akka.pattern.ask
import akka.util.{ByteString, Timeout}
import eu.exante.Main
import eu.exante.actor.ServerHandlerActor.{NewClient, NewMinute}
import eu.exante.util.{AppUtil, TimeUtil}

import scala.collection.mutable
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object ServerHandlerActor {
  case class NewMinute()
  case class NewClient(connection: ActorRef)
}

class ServerHandlerActor extends Actor with ActorLogging {
  import Tcp._

  implicit val timeout = TimeUtil.timeoutForAsk
  var connections = mutable.ListBuffer[ActorRef]()
  val defaultTickTimeout = 60.second
  val newClientNumberOfTime = 10
  val everyMinuteNumberOfTime = 1

  def schedulerTick() = {
    val delay = 59 - LocalDateTime.now.getSecond
    log.info("init tick")
    context.system.scheduler.schedule(delay.second, defaultTickTimeout) {
      implicit val timeout = Timeout(defaultTickTimeout)
      self ! NewMinute
    }
  }

  schedulerTick()

  def receive = {
    case Received(data) =>
      log.debug("receive: " + data)

    case NewClient(client) =>
      connections += client
      context.actorSelection(AppUtil.getActorPath(Main.storageActor)).resolveOne()
          .flatMap { storeRef =>
            (storeRef ? newClientNumberOfTime).mapTo[String]
          }.map { jsonData =>
            sendToClients(jsonData, List(client))
          }.onComplete {
            case Failure(e) => log.error(e.getMessage)
            case Success(_) =>
          }

    case NewMinute =>
      context.actorSelection(AppUtil.getActorPath(Main.storageActor)).resolveOne()
        .flatMap { storeRef =>
          (storeRef ? everyMinuteNumberOfTime).mapTo[String]
        }.map { jsonData =>
          sendToClients(jsonData, connections.toList)
        }.onComplete {
          case Failure(e) => log.error("error: " + e.getMessage)
          case Success(_) =>
        }

    case PeerClosed =>
      log.info("peer close")
      connections -= sender()
  }

  def sendToClients(jsonData: String, clientsRef: List[ActorRef]): Unit = {
    log.debug("Prepare to send for clients: " + jsonData)
    log.debug("Number of client: " + clientsRef.size)
    val rawJsonData = ByteString(jsonData)
    clientsRef.foreach { clientRef =>
      clientRef ! Write(rawJsonData)
    }
  }
}
