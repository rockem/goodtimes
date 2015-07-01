'use strict';


function EventsApi($http) {
    var EVENTS_PATH = '/api/events';

    this.getEvent = function(id, callback) {
        $http.get(EVENTS_PATH + '/' + id).then(function (response) {
           callback(response.data);
        });
    };

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

