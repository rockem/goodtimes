package features

//import com.goodtimes.Application
//import org.openqa.selenium.By
//import org.springframework.boot.SpringApplication
//import sun.jvm.hotspot.interpreter.BytecodeRet

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
    //context = SpringApplication.run(Application.class)
    startBrowser('http://localhost:8080')
    Thread.sleep(4000)
}

After {
    quitBrowser()
    //context.close()
}

Given(~'^I am on the home page$') { ->
    gotoUrl('/')
}

When(~'I submit event details$') { ->
    eventName = 'dinner'
    findElementByName('name').sendKeys(eventName)
    findElementByName('description').sendKeys('kuku\'s dinner')
    findElementByName('name').submit()
}

Then(~'^I should see the event in the events list$') { ->
    assertTrue(getPageSource().contains(eventName))

}