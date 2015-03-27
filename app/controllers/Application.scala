package controllers

import play.api._
import play.api.mvc._
import core.BrowserTest
import org.joda.time.DateTime
import scala.slick.driver.H2Driver.simple._
import com.github.tototoshi.slick.H2JodaSupport._
import models.Tables._
import play.api.Play.current

object Application extends Controller {
  
  def index = Action {
    Redirect("/amounts");
  }
  
  def amounts = Action {
    play.api.db.slick.DB.withSession{ implicit session =>
      History += HistoryRow(0, new DateTime(), "type", 1, "shinsei", 11.0)
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
  
  def jdbctest = Action {
    play.api.db.slick.DB.withSession{ implicit session =>
      History += HistoryRow(0, new DateTime(), "type", 1, "shinsei", 11.0)
      Ok(views.html.amounts(History.list))
    }
  }
  

}