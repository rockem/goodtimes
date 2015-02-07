this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

class EventDetails {

    String eventName = ""
}

class World {}

World {
    World.mixin SeleniumWebDriver, EventDetails
    new World()
}

Before {
    startBrowser('http://localhost:3000')
}

After {
    quitBrowser()
}

Given(~'^I am on the home page$') { ->
    gotoUrl('/')
}

When(~'I submit event details$') { ->
    eventName = 'dinner'
    findElement('event_name').sendKeys(eventName)
    findElement('event_description').sendKeys('kuku\'s dinner')
    findElement('event_name').submit()
}

Then(~'^I should see the event in the events list$') { ->
    assertTrue(getPageSource().contains(eventName))

}