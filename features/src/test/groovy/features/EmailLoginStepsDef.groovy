package features

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~/^I'm not logged in$/) { ->
    // All tests start in logged out state
}

When(~/^I log in$/) { ->
    browser.logInWith(user)
}

Then(~/^I should (not )?be logged in$/) { loggedIn ->
    if(loggedIn == "not ") {
        assert browser.isElementByIdHidden("logout_container"), "I'm not logged out"
    } else {
        assert browser.isElementByIdHidden("login_container"), "I'm not logged in"
    }
}
