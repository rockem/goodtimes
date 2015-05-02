package features

import features.support.GoodtimesWorld
import features.support.UrlHelper

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

World {
    new GoodtimesWorld()
}

Before {
    browser.startBrowser('http://localhost:4123')
}

After {
    browser.quitBrowser()
}

Given(~'^I am on the "(.*)" page$') { page ->
    browser.gotoUrl(UrlHelper.getUrlForPage(page))
}

Then(~/^I should be on the "(.*?)" page$/) { String page ->
    assert UrlHelper.getUrlForPage(page) == browser.currentUrl()
}
