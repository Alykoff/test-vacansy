package eu.exante.actor

import java.time.LocalDateTime

import akka.actor.{Actor, ActorLogging}
import eu.exante.data.Tick
import eu.exante.service.{MemoryStorageService, StorageService}
import eu.exante.util.TimeUtil
import org.json4s.native.JsonMethods._
import org.json4s.JsonDSL._

class StorageActor extends Actor with ActorLogging {
  val storage: StorageService = new MemoryStorageService

  override def receive = {
    case tick: Tick =>
      log.debug("add tick: " + tick.toString)
      storage.addTick(tick)
    case numberOfMinute: Int =>
      val minDate = LocalDateTime.now.minusMinutes(numberOfMinute)
      val jsonTickData =
        compact(render(
          storage
            .getBefore(minDate)
            .map{ x =>
                ("ticker" -> x.ticker) ~
                ("timestamp" -> TimeUtil.toStringWithoutSeconds(x.closeDate)) ~
                ("open" -> x.open) ~
                ("high" -> x.high) ~
                ("low" -> x.low) ~
                ("close" -> x.close) ~
                ("volume" -> x.volume)
            }
        ))
      sender() ! jsonTickData
  }
}
