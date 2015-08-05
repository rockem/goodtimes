package features.support

class GoodtimesWorld {

    GoodtimesClient goodtimesClient = new GoodtimesClient('http://localhost:4123/')

    SeleniumWebDriver browser = new SeleniumWebDriver()

    GoodtimesUser MY_USER = new GoodtimesUser(
            username: "me",
            password: "123456",
            email: "m.m@gmail.com")

    GoodtimesUser OTHER_USER = new GoodtimesUser(
            username: "popov",
            password: "123456",
            email: "p.p@walla.com")

    GoodtimesUser KUKU_USER = new GoodtimesUser(
            username: "kuku",
            password: "123456",
            email: "kuku@gmail.com")

    GoodtimesEvent DINNER_EVENT = new GoodtimesEvent(
            name: "Kuku's dinner",
            description: "Dinner party for four"
    )

}
