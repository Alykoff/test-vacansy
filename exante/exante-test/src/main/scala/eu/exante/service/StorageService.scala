package eu.exante.service

import java.time.LocalDateTime

import eu.exante.data.{Tick, TickerData}

trait StorageService {
  def getBefore(minDate: LocalDateTime): List[TickerData]
  def addTick(tick: Tick): Unit
}
