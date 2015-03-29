package core

import org.fluentlenium.core.FluentAdapter
import org.openqa.selenium.firefox.FirefoxDriver
import play.api._
import org.openqa.selenium.By

object BrowserTest{
  
  val driver = new FirefoxDriver()
  
  def test = {
        val d = new FirefoxDriver()
        val f = new FluentAdapter(d)
        f.goTo("http://pdirect.shinseibank.com/index_jpn.htm")
        f.fill("#main-left-account input").`with`(Play.current.configuration.getString("auth.shinsei.accountcode").get)
        f.executeScript("document.frmLogonInput.fldUserPass.readOnly = false;")
        f.executeScript("document.frmLogonInput.fldUserNumId.readOnly = false;")
        f.executeScript("document.frmLogonInput.chksecmod.checked=false;")
        f.fill("#fldUserNumId").`with`(Play.current.configuration.getString("auth.shinsei.pin").get)
        f.fill("input[name=fldUserPass]").`with`(Play.current.configuration.getString("auth.shinsei.pass").get)
        f.click("input[name=Login]")
        
        Thread.sleep(1000)
        
        f.executeScript("document.frmLogonInput.chksecmod.checked = false;")
        f.executeScript("document.frmLogonInput.fldGridChg1.readOnly = false;")
        f.executeScript("document.frmLogonInput.fldGridChg2.readOnly = false;")
        f.executeScript("document.frmLogonInput.fldGridChg3.readOnly = false;")
        
        val getFromRandomTable = (v: String) => Play.current.configuration.getConfig("auth.shinsei.random").get.getString(v).get
        
        f.fill("#fldGridChg1").`with`(getFromRandomTable(f.find("td strong", 0).getText))
        f.fill("#fldGridChg2").`with`(getFromRandomTable(f.find("td strong", 1).getText))
        f.fill("#fldGridChg3").`with`(getFromRandomTable(f.find("td strong", 2).getText))
        f.click("#loginbutton")
        
        new FluentAdapter(d.switchTo().frame(d.findElementById("menubar"))).executeScript("SubmitTask('RT', 'ACS', '00', '23', 'frmCurrencyAndBal');")
        
        val shinseimain = new FluentAdapter(d.switchTo().parentFrame().switchTo().frame(5))
        
        play.Logger.debug("" + shinseimain.find("table", 3).find("th.right").getText)
        play.Logger.debug("" + shinseimain.find("table", 5).find("th.right").getText)
        
    }
  
  
}