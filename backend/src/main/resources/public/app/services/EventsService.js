'use strict';


function EventsApi($http) {
    var EVENTS_PATH = '/api/events';

    this.getEvents = function (callback) {
        return $http.get(EVENTS_PATH).then(function (response) {
            callback(response.data);
        });
    };
    this.createEvent = function (eventData, callback) {
        $http.post(EVENTS_PATH, eventData).then(function() {
            callback();
        });
    };
}

