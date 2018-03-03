package eu.exante.data

import java.time.LocalDateTime

import eu.exante.util.TimeUtil

case class TickerData(
    ticker: String, open: Double, high: Double, low: Double, close: Double,
    volume: Long, openDate: LocalDateTime, closeDate: LocalDateTime
)

object TickerData {
  val orderedDate: Ordering[LocalDateTime] = TimeUtil.dateOrdering

  def apply(tick: Tick): TickerData = {
    val date = tick.date
    new TickerData(tick.ticker, tick.price, tick.price, tick.price, tick.price, tick.size, date, date)
  }

  def addTick(tickerData: Option[TickerData], newTick: Tick): TickerData = {
    tickerData match {
      case Some(oldTickerData) => addTick(oldTickerData, newTick)
      case None => apply(newTick)
    }
  }

  def addTick(oldTickerData: TickerData, newTick: Tick): TickerData = {
    val date = newTick.date

    val isOpenDate = orderedDate.lt(newTick.date, oldTickerData.openDate)
    val newOpenDate =
      if (isOpenDate) date
      else oldTickerData.openDate
    val newOpen =
      if (isOpenDate) newTick.price
      else oldTickerData.open

    val isCloseDate = orderedDate.gt(date, oldTickerData.closeDate)
    val newCloseDate =
      if (isCloseDate) date
      else oldTickerData.closeDate
    val newClose =
      if (isCloseDate) newTick.price
      else oldTickerData.close

    val newHigh =
      if (newTick.price > oldTickerData.high) newTick.price
      else oldTickerData.high
    val newLow =
      if (newTick.price < oldTickerData.low) newTick.price
      else oldTickerData.low
    val newVolume = oldTickerData.volume + newTick.size

    apply(oldTickerData.ticker, newOpen, newHigh, newLow, newClose, newVolume, newOpenDate, newCloseDate)
  }
}