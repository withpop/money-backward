package models

import Tables._
import Tables.History._
import scala.slick.driver.H2Driver.simple._
import play.api.Play.current

object HistoryRepository {
  
  def findBy(hisType: HistoryType): Seq[HistoryRow] = 
    play.api.db.slick.DB.withSession{ implicit session =>
      History.filter(_.dataType === hisType.code).list.toSeq
  }
  
  def findAssets(): Seq[HistoryRow] = 
    play.api.db.slick.DB.withSession{ implicit session =>
      History.filter(h => h.dataType inSet HistoryType.TotalAmount.code ::
          HistoryType.InvestmentAmount.code :: Nil)
          .list.toSeq
  }
  
}