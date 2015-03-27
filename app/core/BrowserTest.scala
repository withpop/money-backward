package core

import org.fluentlenium.core.FluentAdapter
import org.openqa.selenium.firefox.FirefoxDriver

object BrowserTest{
  
  val driver = new FirefoxDriver()
  
  def test = {
        val f = new FluentAdapter(new FirefoxDriver())
        f.goTo("http://www.google.com")
        f.fill("input[name=q]").`with`("Fluent")
        f.submit("form")
        Thread.sleep(800)
        f.takeScreenShot()
    }
  
  
}