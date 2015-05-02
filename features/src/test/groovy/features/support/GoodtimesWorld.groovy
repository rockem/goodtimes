package features.support

class GoodtimesWorld {

    GoodtimesClient goodtimesClient = new GoodtimesClient('http://localhost:4123/')

    SeleniumWebDriver browser = new SeleniumWebDriver()

    Map context = new HashMap()

}
