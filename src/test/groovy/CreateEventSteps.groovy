import com.goodtimes.Application
import org.springframework.boot.SpringApplication

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

class EventDetails {

    String eventName = ""
}

class World {}

World {
    World.mixin SeleniumWebDriver, EventDetails
    new World()
}

Before {
    context = SpringApplication.run(Application.class)
    startBrowser('http://localhost')
}

After {
    quitBrowser()
    context.close()
}

Given(~'^I am on the home page$') { ->
    gotoUrl('/')
}

When(~'I submit event details$') { ->
    eventName = 'dinner'
    findElementById('event_name').sendKeys(eventName)
    findElementById('event_description').sendKeys('kuku\'s dinner')
    findElementById('event_name').submit()
}

Then(~'^I should see the event in the events list$') { ->
    assertTrue(getPageSource().contains(eventName))

}