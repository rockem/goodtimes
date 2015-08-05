'use strict';

function EventsApi($http) {
    var EVENTS_PATH = '/api/events/';

    this.getEvent = function(id, callback) {
        $http.get(EVENTS_PATH + id).then(function (response) {
           callback(response.data);
        });
    };

    this.getEvents = function (callback) {
        $http.get(EVENTS_PATH).then(function (response) {
            callback(response.data);
        });
    };

    this.createEvent = function (eventData, callback) {
        $http.post(EVENTS_PATH, eventData).then(function() {
            callback();
        });
    };

    this.shareEvent = function(eventId, email, callback) {
        $http.put(EVENTS_PATH + eventId + '/share', {'email': email}).then(function() {
            callback();
        })
    };
}

