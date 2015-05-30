package features.support

import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient
import net.sf.json.JSONObject

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
        def credentials = "$ADMIN_USERNAME:$ADMIN_PASSWORD".bytes.encodeBase64().toString()
        def response = client.delete(
                path: "/api/users/$username",
                headers: [Authorization: "Basic $credentials", "X-XSRF-TOKEN": this.xsrfToken]
        )
        assert response.status == 200
        updateCSRFToken()

    }
}
