package controllers

import play.Logger
import play.api.mvc.Action
import play.api.mvc.Controller
import models.SearchFromRss
import java.util.concurrent.TimeoutException

object Application extends Controller {
  val QUERY_PARAM = "query"

  def index = Action {
    Ok(views.html.info("Hello Play Framework"))
  }

  def search = Action { implicit request =>
    Logger.info("Logging gogo")
    val searchParams = request.queryString.get(QUERY_PARAM)
    val result =
      if (searchParams.isDefined) {
        val jsonResult = SearchFromRss.getRssJsonInfo(searchParams.get)
        if (jsonResult.isSuccess) jsonResult.get
        else "При выполнении запросов к сервисам возникла ошибка." 
      } else "Запрос пуст"
    Ok(views.html.index(result))
  }
}