package features.support

import groovyx.net.http.RESTClient

import java.util.regex.Matcher

class GoodtimesClient {

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
                params: [_csrf: this.xsrfToken]
        )
        assert response.status == 201
    }
}
