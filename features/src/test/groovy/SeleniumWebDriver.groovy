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

    def gotoUrl(url) {
        driver.get("${domain}${url}")
    }

    def findElement(name) {
        driver.findElement(By.name(name))
    }

}
