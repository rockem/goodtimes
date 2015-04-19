package features

import features.common.GoodtimesClient

class GoodtimesWorld {

    GoodtimesClient goodtimesClient = new GoodtimesClient('http://localhost:4123/')

    def browser = new SeleniumWebDriver()


}
