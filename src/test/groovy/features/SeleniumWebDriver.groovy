package features

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
    }

    def findElementById(String id) {
        driver.findElement(By.id(id))
    }

}
