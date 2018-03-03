package eu.exante.util

import java.time.{Instant, LocalDateTime, ZoneId}
import java.util.concurrent.TimeUnit

import akka.util.Timeout

object TimeUtil {
  def getDate(mills: Long) = {
    LocalDateTime.ofInstant(Instant.ofEpochMilli(mills), ZoneId.systemDefault())
  }
  def removeMillisecondsDate(date: Long): LocalDateTime = {
    getDate(date).withNano(0)
  }
  def removeSecondsDate(date: LocalDateTime): LocalDateTime = {
    date.withNano(0).withSecond(0)
  }
  def toStringWithoutSeconds(date: LocalDateTime): String = {
    date.withNano(0).withSecond(0).toString + ":00Z"
  }

  val timeoutForAsk = Timeout(300, TimeUnit.MILLISECONDS)

  val dateOrdering: Ordering[LocalDateTime] = Ordering.by(
    _.atZone(ZoneId.systemDefault()).toInstant.toEpochMilli
  )
  val dateOrderingTuple: Ordering[(LocalDateTime, scala.collection.mutable.Map[String,eu.exante.data.TickerData])] = Ordering.by(
    _._1.atZone(ZoneId.systemDefault()).toInstant.toEpochMilli
  )
}
