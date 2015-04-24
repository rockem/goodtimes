package features

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

String USERNAME = 'username'
String PASSWORD = 'password'

Given(~/^I'm not logged in$/) { ->
    // Currently we have no session
}

Given(~/^I'm a registered user$/) { ->
    context.put(USERNAME, "elis")
    context.put(PASSWORD, "123456")
    goodtimesClient.createUser(context.get(USERNAME), context.get(PASSWORD), "elis@gmail.com")
}

When(~/^I submit my credentials$/) { ->
    browser.submitForm(['username': context.get(USERNAME), 'password': context.get(PASSWORD)])
}

Then(~/^I should be logged in$/) { ->
    assertTrue(browser.getPageSource().contains("Log out"))
}
