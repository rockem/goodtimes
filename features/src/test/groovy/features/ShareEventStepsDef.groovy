package features

import org.openqa.selenium.By

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)


EVENT_NAME = "Turtles for life"
EVENT_DESC = "Turtle petting session"

Given(~/^I've created an event$/) { ->
    browser.createEvent(EVENT_NAME, EVENT_DESC)
}

Given(~/^I'm on the event's details page$/) { ->
    browser.clickOnEvent(EVENT_NAME)
}

    Given(~/^"(.*?)" is a registered user$/) { String arg1 ->
    goodtimesClient.createUser(KUKU_USER)
}

When(~/^I share my event with "(.*?)"$/) { String arg1 ->
    browser.click(By.id('share-event'))
    browser.submitForm(['share-email': KUKU_USER.email])
}

Then(~/^"(.*?)" should have the event as well$/) { String arg1 ->
    goodtimesClient.login(KUKU_USER)
    assert goodtimesClient.getEvents().find {it -> it.name == EVENT_NAME} != null
}