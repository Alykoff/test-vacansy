package utils
import scala.xml.{XML, Elem}
import play.Logger
import models.Pool
import models.PoolUrlToLinks

object Utils {
  def getHostname(link: String): String = {
    link.replaceAll("^(http|https)://(www\\.)?", "")
      .replaceAll("\\/.*$", "")
      .replaceAll("#.*$", "")
      .replaceAll("(.*)\\.(.*)\\.(.*)", "$2.$3")
  }
}