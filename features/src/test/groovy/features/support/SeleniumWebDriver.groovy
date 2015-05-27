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
        sleep(2000)
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
        sleep(20000)
        values.each { k, v ->
            findElementByName(k).sendKeys(v)
        }
        driver.findElementByName(values.keySet().toList().first()).submit()
        sleep(30000)
    }


}
