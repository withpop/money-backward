package models

import scala.slick.driver.H2Driver.simple._
import com.github.tototoshi.slick.H2JodaSupport._
import org.joda.time.DateTime

object Tables extends Tables

trait Tables {
  sealed abstract class HistoryType(val code: String)
  object HistoryType{
    case object TotalAmount extends HistoryType("Total")
    case object Deposit extends HistoryType("Deposit")
    case object InvestmentAmount extends HistoryType("InvestmentAmount")
    case object Withdrawal extends HistoryType("Withdrawal")
  }
  
  case class HistoryRow(
      id: Long,
      historyDate: DateTime, 
      dataType: String, 
      dataGroup: Int, 
      account: String,
      amount: BigDecimal)
  
  class History(tag: Tag) extends Table[HistoryRow](tag, "History") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def historyDate = column[DateTime]("historyDate")
    def dataType = column[String]("dataType")
    def dataGroup = column[Int]("dataGroup")
    def account = column[String]("account")
    def amount = column[BigDecimal]("amount")
    
    def * = (id, historyDate, dataType, dataGroup, account, amount) <> (HistoryRow.tupled, HistoryRow.unapply _)
    
  }
  
  lazy val History = TableQuery[History]
  
  
  case class RegularItemRow(
      id: Long,
      name: String, 
      itemType: String, // income or outgo
      amount: BigDecimal)
  
  class RegularItem(tag: Tag) extends Table[RegularItemRow](tag, "RegularItem") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def itemType = column[String]("itemType")
    def amount = column[BigDecimal]("amount")
    
    def * = (id, name, itemType, amount) <> (RegularItemRow.tupled, RegularItemRow.unapply _)
    
  }
  
  lazy val RegularItem = TableQuery[RegularItem]
  
  
  case class IrregularItemRow(
      id: Long,
      occurDate: DateTime,
      name: String, 
      itemType: String, // income or outgo
      amount: BigDecimal)
  
  class IrregularItem(tag: Tag) extends Table[IrregularItemRow](tag, "IrregularItem") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def occurDate = column[DateTime]("occurDate")
    def name = column[String]("name")
    def itemType = column[String]("itemType")
    def amount = column[BigDecimal]("amount")
    
    def * = (id, occurDate, name, itemType, amount) <> (IrregularItemRow.tupled, IrregularItemRow.unapply _)
    
  }
  
  lazy val IrregularItem = TableQuery[IrregularItem]


}