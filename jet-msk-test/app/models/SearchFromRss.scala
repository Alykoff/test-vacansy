package models

import scala.util.Try

import play.api.libs.json.Json
import utils.Utils

object SearchFromRss {
  val BASE_LINK = "http://blogs.yandex.ru/search.rss?text="
  val COUNT_THREADS = 6
  val poolUrlToXml = new PoolUrlToLinks(COUNT_THREADS)

  def queryToUrl(queries: Seq[String]): Seq[String] = queries.map(BASE_LINK + _)

  def linksToFlatJson(links: Seq[Seq[String]]): String = {
    val uniqLinks = links.flatten.toSet
      val statLinks = uniqLinks.groupBy((y: String) => Utils.getHostname(y))
        .map(z => z._1 -> z._2.size)
      val json = Json.toJson(statLinks)
      Json.prettyPrint(json)
  }
  
  def getRssJsonInfo(queries: Seq[String]): Try[String] = {
    val urls = queryToUrl(queries)
    poolUrlToXml.execute(urls).map {linksToFlatJson}
  }
}

