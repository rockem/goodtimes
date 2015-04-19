package features.support

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver

class SeleniumWebDriver {

    WebDriver driver;
    String domain;

    def startBrowser(domain) {
        driver = new FirefoxDriver()
        this.domain = domain
    }

    def quitBrowser() {
        driver.quit()
    }

    def gotoUrl(String url) {
        driver.get("${domain}${url}")
        sleep(1000)
    }

    def currentUrl() {
        driver.currentUrl.replace(domain, "")
    }

    def findElementById(String id) {
        driver.findElement(By.id(id))
    }

    def findElementByName(String name) {
        driver.findElement(By.name(name))
    }

    def getPageSource() {
        driver.getPageSource()
    }

    def submitForm(def values) {
        values.each { k, v ->
            findElementByName(k).sendKeys(v)
        }
        driver.findElementByName(values.keySet().toList().first()).submit()
        sleep(2000)
    }

    def logInWith(GoodtimesUser user) {
        this.gotoUrl(UrlHelper.getUrlForPage("login"))
        this.submitForm(['login-username': user.getUsername(), 'login-password': user.getPassword()])
    }


}
