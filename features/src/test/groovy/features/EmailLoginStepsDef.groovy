package features

import cucumber.api.PendingException

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~/^I'm not logged in$/) { ->
}

Given(~/^I'm a registered user$/) { ->
    goodtimesClient.createUser(username, password, email)
}

When(~/^I submit my credentials$/) { ->
    browser.submitForm(['username': username, 'password': password])
}

When(~/^I submit my credentials$/) { ->
}

Then(~/^I should be logged in$/) { ->
}