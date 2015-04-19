package features.support

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient
import net.sf.json.JSONObject

import java.lang.reflect.Type
import java.util.regex.Matcher

class GoodtimesClient {

    private static final String ADMIN_USERNAME = "goodtimesAdmin"
    private static final String ADMIN_PASSWORD = "ksud7dksD3rdTGS345skdnsfhk"

    private def client
    private String xsrfToken

    GoodtimesClient(String domain) {
        this.client = new RESTClient(domain)
        updateCSRFToken()
    }

    def updateCSRFToken() {
        def response = client.get(path: "/")
        Matcher myMatcher = response.getHeaders('Set-Cookie').toString() =~ /XSRF-TOKEN=([a-z0-9\-]+)/
        if(myMatcher.find())
            xsrfToken = myMatcher.group(1)
    }

    def createUser(GoodtimesUser user) {
        def response = client.post(
                path: "/api/users",
                body: JSONObject.fromObject(user),
                requestContentType : ContentType.JSON,
                headers: ["X-XSRF-TOKEN" : this.xsrfToken]
        )
        assert response.status == 201
    }

    def deleteUser(username) {
        adminDelete("/api/users/$username")
    }

    def adminDelete(path) {
        def credentials = "$ADMIN_USERNAME:$ADMIN_PASSWORD".bytes.encodeBase64().toString()
        def response = client.delete(
                path: "$path",
                headers: [Authorization: "Basic $credentials", "X-XSRF-TOKEN": this.xsrfToken]
        )
        assert response.status == 200
        updateCSRFToken()
    }

    def deleteAllEvents() {
        adminDelete("/api/events")
    }

    def deleteEvent(def name, def desc) {
        def response = client.get(
                path: '/api/events',
                headers: [Authorization: "Basic $credentials", "X-XSRF-TOKEN": this.xsrfToken]
        )
        //allEvents = 'http://localhost:8888/api/events'.toURL().text

        filterBy(response.body, name, desc).each {
            def r = client.delete(
                    path: "/api/users/$it",
                    headers: [Authorization: "Basic $credentials", "X-XSRF-TOKEN": this.xsrfToken]
            )
            assert r.status == 200
        }
    }

    private List filterBy(String allEvents, name, desc) {
        Type listType = new TypeToken<List<GoodtimesEvent>>(){}.getType()
        new Gson().fromJson(allEvents, listType)
                .findAll { it.name == name && it.description == desc }*.id
    }


}
