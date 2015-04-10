package controllers

import play.api._
import play.api.mvc._
import core.BrowserTest
import org.joda.time.DateTime
import scala.slick.driver.H2Driver.simple._
import com.github.tototoshi.slick.H2JodaSupport._
import models.Tables._
import play.api.Play.current
import core.Scraping

object Application extends Controller {
  
  def jsoup = Action{
    Ok(Scraping.test).as("text/plain")
  }
  
  def index = Action {
    Redirect("/amounts");
  }
  
  def amounts = Action {
    play.api.db.slick.DB.withSession{ implicit session =>
      Ok(views.html.amounts(History.list))
    }
  }
  
  def cashflow = TODO 
  
  def longterm = TODO 
  
  def setting = TODO 
  
  def fetch = Action{
    
    Redirect("/")
  }
  
  def webtest = Action {
    
    //System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe")
    
    BrowserTest.test
    
    Ok(views.html.index("Your new application is ready."))
  }
 
  def addTestData = Action {
    play.api.db.slick.DB.withSession{ implicit session =>
      History += HistoryRow(0, "", new DateTime(2015, 3, 1, 0, 0, 0), HistoryType.TotalAmount.code, 1, "shinsei", 10000.0)
      History += HistoryRow(0, "", new DateTime(2015, 3, 1, 0, 0, 0), HistoryType.InvestmentAmount.code, 1, "shinsei", 20000.0)
      History += HistoryRow(0, "食品", new DateTime(2015, 3, 2, 0, 0, 0), HistoryType.CreditPayment.code, 1, "jcb", 1000.0)
      History += HistoryRow(0, "", new DateTime(2015, 3, 3, 0, 0, 0), HistoryType.Deposit.code, 1, "shinsei", 2000.0)
      History += HistoryRow(0, "", new DateTime(2015, 3, 3, 0, 0, 0), HistoryType.Withdrawal.code, 1, "shinsei", 1000.0)
      History += HistoryRow(0, "", new DateTime(2015, 3, 4, 0, 0, 0), HistoryType.TotalAmount.code, 1, "shinsei", 11000.0)
      History += HistoryRow(0, "", new DateTime(2015, 3, 5, 0, 0, 0), HistoryType.Withdrawal.code, 1, "shinsei", 2000.0)
      History += HistoryRow(0, "", new DateTime(2015, 3, 6, 0, 0, 0), HistoryType.InvestmentAmount.code, 1, "shinsei", 20010.0)
      Ok(views.html.amounts(History.list))
    }
  }
  

}