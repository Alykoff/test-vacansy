package eu.exante

import akka.actor.{ActorSystem, Props}
import java.net.InetSocketAddress

import eu.exante.actor._
import org.slf4j.LoggerFactory


object Main {
  val log = LoggerFactory.getLogger(Main.getClass)

  val clientType = "client"
  val typeProperty = "type"

  val systemName = "TCPSystem"
  val serverNameActor = "TCPServerActor"
  val tickerPlanNameActor = "tickerPlanActor"
  val clientNameActor = "TCPClientActor"
  val storageActor = "storageActor"
  val serverHandlerNameActor = "serverHandlerActor"

  lazy val tcpServerAddress = {
    new InetSocketAddress("localhost", 8842)
  }

  lazy val remoteTickerSourceAddress = {
    new InetSocketAddress("localhost", 5555)
  }

  def main(args: Array[String]): Unit = {
    val isClientType = System.getProperty(typeProperty) == clientType
    log.info("Is client type: " + isClientType)

    val system = ActorSystem(systemName)
    if (isClientType) runClient(system)
    else runServer(system)
  }

  def runServer(system: ActorSystem) = {
    system.actorOf(Props(classOf[TcpServerActor], tcpServerAddress), serverNameActor)
    system.actorOf(Props(classOf[TickerPlantActor], remoteTickerSourceAddress), tickerPlanNameActor)
    system.actorOf(Props[StorageActor], storageActor)
    system.actorOf(Props[ServerHandlerActor], serverHandlerNameActor)
  }

  def runClient(system: ActorSystem) = {
    system.actorOf(Props(classOf[TestTickClient], tcpServerAddress), clientNameActor)
  }

}
