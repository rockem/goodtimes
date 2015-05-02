package features

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

eventName = 'dinner'

Given(~'^I am on the home page$') { ->
    browser.gotoUrl('/')
}

When(~'I submit event details$') { ->
    browser.submitForm(['event-name': 'dinner', 'event-description': 'kuku\'s dinner'])
}

Then(~'^I should see the event in the events list$') { ->
    assert browser.getPageSource().contains(eventName)

}