package eu.exante.data

import java.time.LocalDateTime

import akka.util.ByteString
import eu.exante.util.TimeUtil

case class Tick(date: LocalDateTime, ticker: String, price: Double, size: Long, isValid: Boolean)

object Tick {
  val sizeOfLenField = 2
  val sizeOfTimestampField = 8
  val sizeOfTickerLenField = 2
  val sizeOfPriceField = 8
  val sizeOfSizeField = 4
  val encoding = "ASCII"

  def getTick(data: ByteString): Tick = {
    var dataRaw = data
    val len = data.take(sizeOfLenField).asByteBuffer.asShortBuffer().get()
    dataRaw = dataRaw.drop(sizeOfLenField)
    val timestamp = dataRaw.take(sizeOfTimestampField).asByteBuffer.asLongBuffer().get()
    dataRaw = dataRaw.drop(sizeOfTimestampField)
    val tickerLen = dataRaw.take(sizeOfTickerLenField).asByteBuffer.asShortBuffer().get()
    dataRaw = dataRaw.drop(sizeOfTickerLenField)
    val ticker = dataRaw.take(tickerLen).decodeString(encoding)
    dataRaw = dataRaw.drop(tickerLen)
    val price = dataRaw.take(sizeOfPriceField).asByteBuffer.asDoubleBuffer().get()
    dataRaw = dataRaw.drop(sizeOfPriceField)
    val size = dataRaw.take(sizeOfSizeField).asByteBuffer.asIntBuffer().get()

    val isLenValid =
      len == (
        sizeOfTimestampField + sizeOfTickerLenField + tickerLen + sizeOfPriceField + sizeOfSizeField
      )

    Tick(TimeUtil.removeMillisecondsDate(timestamp), ticker, price, size, isLenValid)
  }
}
