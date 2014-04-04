package models

import java.util.concurrent.Executors
import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.Await
import scala.concurrent.Future
import play.Logger
import scala.util.Try
import scala.util.Failure
import scala.util.Success

trait Pool[A, B] {
  def poolCount = 2
  def awaitTimeout = 20.second

  implicit def execctx = new ExecutionContext {
    val threadPool = Executors.newFixedThreadPool(poolCount)
    def execute(runnable: Runnable) = {
      threadPool.submit(runnable)
    }
    def reportFailure(t: Throwable) = {

    }
  }

  def execute(datas: Seq[A], func: A => B): Try[Seq[B]] = {
    val tasks: Seq[Future[B]] = for (data <- datas) yield future {
      func(data)
    }
    val aggregated: Future[Seq[B]] = Future.sequence(tasks)
    try {
    	Success(Await.result(aggregated, awaitTimeout))
    } catch {
      case e: TimeoutException => Failure(e)
    }
  }
}