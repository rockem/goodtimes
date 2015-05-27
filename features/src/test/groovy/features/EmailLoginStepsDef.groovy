package features

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

String USERNAME = 'username'
String PASSWORD = 'password'
String EMAIL = 'email'

Given(~/^I'm not logged in$/) { ->
    context.put(USERNAME, "elis")
    context.put(PASSWORD, "123456")
    context.put(EMAIL, "elis@gmail.com")
    goodtimesClient.deleteUser(context.get(USERNAME))
}

Given(~/^I'm a registered user$/) { ->
    goodtimesClient.createUser(context.get(USERNAME), context.get(PASSWORD), context.get(EMAIL))
}

When(~/^I submit my credentials$/) { ->
    browser.submitForm(['login-username': context.get(USERNAME), 'login-password': context.get(PASSWORD)])
}

Then(~/^I should be logged in$/) { ->
    assert goodtimesClient.isLoggedIn()
    assert browser.getPageSource().contains("Log out")
}
