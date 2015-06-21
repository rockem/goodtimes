package features.support

class GoodtimesWorld {

    GoodtimesClient goodtimesClient = new GoodtimesClient('http://localhost:4123/')

    SeleniumWebDriver browser = new SeleniumWebDriver()

    static GoodtimesUser MY_USER = new GoodtimesUser(
            username: "kuku",
            password: "123456",
            email: "k.k@gmail.com")

    static GoodtimesUser OTHER_USER = new GoodtimesUser(
            username: "popov",
            password: "123456",
            email: "p.p@walla.com")

    static GoodtimesEvent DINNER_EVENT = new GoodtimesEvent(
            name: "Kuku's dinner",
            description: "Dinner party for four"
    )

}
