'use strict';


function EventsApi($http) {
    var EVENTS_PATH = '/api/events';

    this.getEvents = function () {
        return $http.get(EVENTS_PATH).then(function (response) {
            return response.data;
        });
    };
    this.createEvent = function (eventData) {
        return $http.post(EVENTS_PATH, eventData);
    };
}

