package models
import scala.xml.{ XML, Elem }
import play.Logger
import utils.Utils
import java.util.concurrent.TimeoutException
import scala.util.Try

class PoolUrlToLinks(val countThreads: Int) extends Pool[String, Seq[String]] {
  override def poolCount = countThreads
  val NUM_LINKS_FROM_ONE_PAGE = 10

  def urlToLinks(query: String): Seq[String] = {
    try {
      val xmlSource = getXml(query)
      val links = { xmlSource \\ "item" \ "link" }.take(NUM_LINKS_FROM_ONE_PAGE)
      links.map(_.text)
    } catch {
      case e: Exception => Seq.empty[String]
    }
  }

  def execute(datas: Seq[String]): Try[Seq[Seq[String]]] = execute(datas, urlToLinks)
  
  def getXml(url: String): Elem = XML.load(url)
}