package features

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)


When(~/^I log out$/) { ->
    browser.findElementByName('logout').click()
    sleep(1000)
}