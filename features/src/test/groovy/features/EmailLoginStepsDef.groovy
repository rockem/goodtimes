package features

import cucumber.api.PendingException

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~/^I'm not logged in$/) { ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}

Given(~/^I'm a registered user$/) { ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}

When(~/^I submit my credentials$/) { ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}

Then(~/^I should be logged in$/) { ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}