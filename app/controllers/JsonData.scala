package controllers

import play.api._
import play.api.mvc._
import core.BrowserTest
import org.joda.time.DateTime
import scala.slick.driver.H2Driver.simple._
import com.github.tototoshi.slick.H2JodaSupport._
import models.Tables._
import play.api.Play.current
import models._
import play.api.libs.json.Json

object JsonData extends Controller {
  
  def assets = Action {
    val results = HistoryRepository
      .findAssets()
      .groupBy(_.historyDate.toLocalDate())
      .map{case (date, r) => (date,
          r.filter(_.dataType == HistoryType.TotalAmount.code).map(_.amount).headOption.getOrElse(BigDecimal(0)),
          ( BigDecimal(0) /: r)(_ + _.amount))}
    
    
    Ok(Json.obj(
          "labels" -> results.map(_._1),
          "datasets" -> Json.arr(Json.obj (
                "label" -> "現金",
                "fillColor" -> "rgba(220,220,220,0.2)",
                "strokeColor" -> "rgba(220,220,220,1)",
                "pointColor" -> "rgba(220,220,220,1)",
                "pointStrokeColor" -> "#fff",
                "pointHighlightFill" -> "#fff",
                "pointHighlightStroke" -> "rgba(220,220,220,1)",
                "data" -> results.map{ case (_, cash, _) => cash.toInt }
              ), 
              Json.obj (
                "label" -> "現金 + 資産",
                "fillColor" -> "rgba(220,220,220,0.2)",
                "strokeColor" -> "rgba(220,220,220,1)",
                "pointColor" -> "rgba(220,220,220,1)",
                "pointStrokeColor" -> "#fff",
                "pointHighlightFill" -> "#fff",
                "pointHighlightStroke" -> "rgba(220,220,220,1)",
                "data" -> results.map{ case (_, _, total) => total.toInt }
              )))
        )
  }
  
  
  
  
}