package features

import features.support.GoodtimesWorld
import features.support.UrlHelper

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

World {
    new GoodtimesWorld()
}

Before {
    goodtimesClient.deleteAllEvents()
    goodtimesClient.deleteUser(MY_USER.getUsername())
    goodtimesClient.createUser(MY_USER)
    browser.startBrowser('http://localhost:4123')
}

Before("@LoggedIn") {
    browser.logInWith(MY_USER)
}

After {
    browser.quitBrowser()
}

Given(~'^I am on the "(.*)" page$') { page ->
    browser.gotoUrl(UrlHelper.getUrlForPage(page))
}

Then(~/^I should be on the "(.*?)" page$/) { String page ->
    assert browser.currentUrl().startsWith(UrlHelper.getUrlForPage(page))
}
