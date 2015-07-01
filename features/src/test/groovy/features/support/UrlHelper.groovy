package features.support

class UrlHelper {

    static String getUrlForPage(String page) {
        if(page.toLowerCase() == "home") {
            return "/#/"
        } else if(page.toLowerCase() == "event's details") {
            return "/#/event"
        }

        return "/#/" + page
    }
}
