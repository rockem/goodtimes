package features

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

When(~/^I click on "(.*?)"$/) { String signup ->
    browser.findElementByName(signup).click();
}

When(~/^I submit Poppi's registration information$/) { ->
    browser.submitForm(['signup-username': NEW_USER.getUsername(), 'signup-email': NEW_USER.getEmail(), 'signup-password': NEW_USER.getPassword()]);
}

Then(~/^I should be logged in as "(.*?)"$/) { String username ->
    assert browser.isElementByIdHidden("login_container"), "I'm not logged in"
    browser.findElementByName(username)
}
