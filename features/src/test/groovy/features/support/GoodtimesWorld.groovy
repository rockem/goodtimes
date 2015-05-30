package features.support

class GoodtimesWorld {

    GoodtimesClient goodtimesClient = new GoodtimesClient('http://localhost:4123/')

    SeleniumWebDriver browser = new SeleniumWebDriver()

    GoodtimesUser user = new GoodtimesUser(
            username: "kuku",
            password: "123456",
            email: "k.k@gmail.com")

}
