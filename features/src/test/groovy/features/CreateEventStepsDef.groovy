package features

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

World {
    new GoodtimesWorld()
}

Before {
    browser.startBrowser('http://localhost:8888/')
}

After {
    browser.quitBrowser()
}

Given(~'^I am on the home page$') { ->
    browser.gotoUrl('/')
}

When(~'I submit event details$') { ->
    eventName = 'dinner'
    browser.findElementByName('name').sendKeys(eventName)
    browser.findElementByName('description').sendKeys('kuku\'s dinner')
    browser.findElementByName('name').submit()
}

Then(~'^I should see the event in the events list$') { ->
    assertTrue(browser.getPageSource().contains(eventName))

}