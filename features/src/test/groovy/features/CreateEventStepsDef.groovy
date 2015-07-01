package features

import features.support.UrlHelper

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

When(~'I submit event details$') { ->
    browser.submitForm(['event-name': DINNER_EVENT.getName(), 'event-description': DINNER_EVENT.getDescription()])
}

When(~'I click on create event') { ->
    browser.findElementById('create-event').click()
    sleep(1)
}

Then(~'^I should see the event in the events list$') { ->
    assert browser.getPageSource().contains(DINNER_EVENT.getName())
}

Given(~/^other user has events$/) { ->
    goodtimesClient.createUser(OTHER_USER)
    goodtimesClient.createEvent(DINNER_EVENT)
}

Then(~/^I should have (\d+) events$/) { int numOfEvents ->
    browser.gotoUrl(UrlHelper.getUrlForPage('event-list'))
    assert browser.rowCountFor('events-list') == numOfEvents
}