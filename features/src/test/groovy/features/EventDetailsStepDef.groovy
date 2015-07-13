package features

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

EVENT_NAME = 'party'
EVENT_DESC = 'Party on with kuku'

Given(~/^I have events$/) { ->
    browser.createEvent(EVENT_NAME, EVENT_DESC)
}

When(~/^I click on an event$/) { ->
    browser.clickOnEvent(EVENT_NAME)
}

Then(~/^I should see the event details$/) { ->
    assert browser.contains(EVENT_NAME) && browser.contains(EVENT_DESC)
}