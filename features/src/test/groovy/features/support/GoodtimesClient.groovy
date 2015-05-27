package features.support

import groovyx.net.http.RESTClient
import sun.misc.BASE64Encoder

import java.util.regex.Matcher

class GoodtimesClient {

    private static final String ADMIN_USERNAME = "goodtimesAdmin"
    private static final String ADMIN_PASSWORD = "ksud7dksD3rdTGS345skdnsfhk"

    private RESTClient client
    private String xsrfToken

    GoodtimesClient(String domain) {
        this.client = new RESTClient(domain)
        updateCSRFToken()
    }

    def updateCSRFToken() {
        def response = client.get(path: "/")
        Matcher myMatcher = response.getHeaders('Set-Cookie').toString() =~ /XSRF-TOKEN=([a-z0-9\-]+)/
        assert myMatcher.find()
        xsrfToken = myMatcher.group(1)
    }

    def createUser(String username, String password, String email) {
        def response = client.post(
                path: "/api/users",
                body: [username: username, password: password, email: email],
                requestContentType : "application/json",
                params: [_csrf: this.xsrfToken],
                headers: [Authorization : ""]
        )
        assert response.status == 201
    }

    def isLoggedIn() {
        def response = client.get(path: "/user")
        return response.status == 200
    }

    def deleteUser(username) {
        def credentials = "$ADMIN_USERNAME:$ADMIN_PASSWORD".bytes.encodeBase64().toString()
        def response = client.delete(
                path: "/api/users/$username",
                params: [_csrf: this.xsrfToken],
                headers: [Authorization : "Basic $credentials"]
        )
        assert response.status == 200
        response = client.get(path: "/user")
        print("dfsf")
    }
}
