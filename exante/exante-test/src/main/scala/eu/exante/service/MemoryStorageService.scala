package eu.exante.service

import java.time.LocalDateTime
import java.util.concurrent.locks.ReentrantReadWriteLock

import eu.exante.data.{Tick, TickerData}
import eu.exante.util.TimeUtil
import eu.exante.util.TimeUtil.{dateOrdering, dateOrderingTuple}

import scala.collection.mutable

class MemoryStorageService extends StorageService {
  implicit val dateOrder: Ordering[LocalDateTime] = dateOrdering
  implicit val dateOrderStorage: Ordering[(LocalDateTime, scala.collection.mutable.Map[String,eu.exante.data.TickerData])] = dateOrderingTuple

  val buckets = new mutable.TreeMap[LocalDateTime, mutable.Map[String, TickerData]]()(dateOrder)
  val lock = new ReentrantReadWriteLock
  val numOfBuckets = 10

  override def getBefore(minDate: LocalDateTime): List[TickerData] = {
    try {
      lock.readLock().lock()
      buckets
        .flatMap(_._2.values)
        .toList
        .filter(tickerData => dateOrder.gteq(tickerData.closeDate, minDate))
    } finally {
      lock.readLock().unlock()
    }
  }

  override def addTick(tick: Tick): Unit = {
    try {
      lock.writeLock().lock()
      val dateWithoutSec = TimeUtil.removeSecondsDate(tick.date)
      val ticker = tick.ticker

      if (buckets.nonEmpty && dateOrder.lt(dateWithoutSec, buckets.min._1)) {
        return
      }

      val newBucket = buckets.filterKeys(
          key => key.equals(dateWithoutSec)
        ).map{ case (key, bucket) =>
          val newBucket =
            bucket.filterKeys(
              label => label.equals(ticker)
            ).map {
              case(_, tickerData) => tickerData
            }.headOption

          bucket += (ticker -> TickerData.addTick(newBucket, tick))
          bucket
        }
        .headOption
        .getOrElse(
          mutable.Map(ticker -> TickerData(tick))
        )

      buckets += (dateWithoutSec -> newBucket)
      while (buckets.size > numOfBuckets) {
        buckets.remove(buckets.min._1)
      }
    } finally {
      lock.writeLock().unlock()
    }
  }

  override def toString: String = {
    s"""
      |Number of min: ${buckets.size};
      |Number of els: ${buckets.flatMap(_._2.values).toList.size}
      |${buckets.flatMap(_._2.values).toList}
      |${buckets.toString}
    """.stripMargin
  }
}