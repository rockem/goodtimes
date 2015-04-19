package features

import features.support.UrlHelper

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~/^I'm not logged in$/) { ->
    browser.gotoUrl(UrlHelper.getUrlForPage("home"))
    assert browser.getPageSource().contains("Login")
}

When(~/^I log in$/) { ->
    browser.logInWith(user)
}

Then(~/^I should be logged in$/) { ->
    assert browser.getPageSource().contains("Log out")
}
