package features.support

class UrlHelper {

    static String getUrlForPage(String page) {
        if(page.toLowerCase() == "home") {
            return "/#/"
        }
        return "/#/" + page
    }
}
