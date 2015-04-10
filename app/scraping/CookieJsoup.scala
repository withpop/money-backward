package scraping

import org.jsoup.Jsoup
import org.jsoup.Connection.Response
import collection.JavaConversions._
import org.jsoup.Connection

class CookieJsoup(
    var userAgent: String = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36",
    var followRedirect: Boolean = true,
    var timeout: Int = 180000,
    var ignoreHttpErrors: Boolean = true
) extends {
  
  private val cookies: collection.mutable.Map[String, String] = collection.mutable.Map[String, String]()
    
  def withCookie(url: String, r: Response): Connection = {
    this.cookies ++= r.cookies
    Jsoup.connect(url)
    .userAgent(userAgent)
    .followRedirects(followRedirect)
    .timeout(timeout)
    .ignoreHttpErrors(ignoreHttpErrors)
    .cookies(this.cookies)    
  }
  
  
}