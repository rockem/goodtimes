package features

import cucumber.api.PendingException

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~/^I'm not logged in$/) { ->
    throw new PendingException()
}

Given(~/^I'm a registered user$/) { ->
    goodtimesClient.createUser(username, password, email)
}

When(~/^I submit my credentials$/) { ->
    browser.submitForm(['username': username, 'password': password])
}

Then(~/^I should be logged in$/) { ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}