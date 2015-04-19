package features.support

import groovyx.net.http.RESTClient

class GoodtimesClient {

    private RESTClient client

    GoodtimesClient(String domain) {
        this.client = new RESTClient(domain)
    }

    def createUser(String username, String password, String email) {
        def response = client.post(
                path: "/api/users",
                body: [username: username, password: password, email: email],
                requestContentType : "application/json"
        )
        assert response.status == 201
    }
}
