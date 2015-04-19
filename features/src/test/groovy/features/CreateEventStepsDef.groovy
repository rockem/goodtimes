package features

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

eventName = 'dinner'

World {
    new GoodtimesWorld()
}

Before {
    browser.startBrowser('http://localhost:4123/app')
}

After {
    browser.quitBrowser()
}

Given(~'^I am on the home page$') { ->
    browser.gotoUrl('/')
}

When(~'I submit event details$') { ->
    browser.submitForm(['name': 'dinner', 'description': 'kuku\'s dinner'])
}

Then(~'^I should see the event in the events list$') { ->
    assert browser.getPageSource().contains(eventName)

}