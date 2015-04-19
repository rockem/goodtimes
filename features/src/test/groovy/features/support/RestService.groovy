package features.support

import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.Method

import java.util.stream.Collector
import java.util.stream.Collectors

@Deprecated
class RestService {

    private String baseUrl
    private HTTPBuilder httpBuilder
    private Map<String, String> cookies = new HashMap<>()
    //private List<String> cookies = []
    private String xsrf = "";

    RestService(String domain) {
        this.baseUrl = domain
        this.httpBuilder = initializeHttpBuilder()
    }

    def delete(String path, String auth = null) {
        return httpBuilder.request(Method.DELETE) { request ->
            uri.path = path
            headers['Cookie'] = createRequestCookies()
            headers['X-XSRF-TOKEN'] = xsrf
            if(auth != null) {
                headers['Authorization'] = auth
            }
        }
    }

    private String createRequestCookies() {
        cookies.collect { k, v -> "$k=$v" }.join(";")
    }

    def get(path, auth = null) {
        return httpBuilder.request(Method.GET) { request ->
            uri.path = path
            headers['Cookie'] = createRequestCookies()
            headers['X-XSRF-TOKEN'] = xsrf
            if(auth != null) {
                headers['Authorization'] = auth
            }
        }
    }

    def post(path, b, auth = null) {
        return httpBuilder.request(Method.POST, ContentType.JSON) { request ->
            uri.path = path
            body = b
            headers['Cookie'] = createRequestCookies()
            headers['X-XSRF-TOKEN'] = xsrf
            if(auth != null) {
                headers['Authorization'] = auth
            }
        }
    }

    def request(Method method, ContentType contentType, String url, Map<String, Serializable> params) {
        debug("Send $method request to ${this.baseUrl}$url: $params")
        httpBuilder.request(method, contentType) { request ->
            uri.path = url
            uri.query = params
            uri.
            headers['Cookie'] = cookies.join(';')
        }
    }

    private HTTPBuilder initializeHttpBuilder() {
        def httpBuilder = new HTTPBuilder(baseUrl)

        httpBuilder.handler
        httpBuilder.handler.success = { HttpResponseDecorator resp, reader ->
            resp.getHeaders('Set-Cookie').each {
                //[Set-Cookie: JSESSIONID=E68D4799D4D6282F0348FDB7E8B88AE9; Path=/frontoffice/; HttpOnly]
                String cookie = it.value.split(';')[0]
                String[] pair = cookie.split('=')
                if(pair[0] == "XSRF-TOKEN") {
                    xsrf = pair[1]
                } else {
                    debug("Adding cookie to collection: $cookie")
                    cookies.put(pair[0].replace("XSRF-TOKEN", "X-XSRF-TOKEN"), pair[1])
                    //cookies.add(cookie)
                }
            }
            debug("Response: ${reader}")
            return reader
        }
        return httpBuilder
    }


    private debug(String message) {
        System.out.println(message) //for Gradle
    }
}
