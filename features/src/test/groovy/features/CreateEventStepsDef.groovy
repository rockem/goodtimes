package features
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import features.common.GTE
import groovyx.net.http.RESTClient

import java.lang.reflect.Type

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

eventName = 'dinner'
eventDescription = 'kuku\'s dinner'

World {
    new GoodtimesWorld()
}

Before {
    browser.startBrowser('http://localhost:8888/app')
}

After {
    browser.quitBrowser()
    deleteEvents(eventName, eventDescription)
}

Given(~'^I am on the home page$') { ->
    browser.gotoUrl('/')
}

When(~'I submit event details$') { ->
    browser.findElementByName('event-name').sendKeys(eventName)
    browser .findElementByName('event-description').sendKeys(eventDescription)
    browser.findElementByName('event-name').submit()
    sleep(3000)
}

Then(~'^I should see the event in the events list$') { ->
    assert browser.getPageSource().contains(eventName)

}

def deleteEvents(def name, def desc) {
    restClient = new RESTClient('http://localhost:8888')
    allEvents = 'http://localhost:8888/api/events'.toURL().text

    filterBy(allEvents, name, desc).each {
        restClient.delete(path: "/api/events/" + it)
    }
    assert restClient.get(path: "/api/events").data.isEmpty()
}

private List filterBy(String allEvents, name, desc) {
    Type listType = new TypeToken<List<GTE>>(){}.getType()
    new Gson().fromJson(allEvents, listType)
            .findAll { it.name == name && it.description == desc }*.id
}