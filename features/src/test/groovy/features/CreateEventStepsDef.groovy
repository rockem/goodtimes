package features

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~'I have no events$') { ->
    goodtimesClient.deleteAllEvents()
}

When(~'I submit event details$') { ->
    browser.submitForm(['event-name': dinnerEvent.getName(), 'event-description': dinnerEvent.getDescription()])
}

Then(~'^I should see the event in the events list$') { ->
    assert browser.getPageSource().contains(dinnerEvent.getName())

}