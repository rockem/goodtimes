import cucumber.api.PendingException

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~'^I am on the main page$') { ->
    // Express the Regexp above with the code you wish you had
    throw new PendingException()
}

When(~'I fill in event details$') { ->
    throw new PendingException()
}

When(~'^I submit$') { ->
    throw new PendingException()
}

Then(~'^I should see the event in the events list$') { ->
    throw new PendingException()

}