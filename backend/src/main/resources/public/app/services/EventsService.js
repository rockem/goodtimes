'use strict';


function EventsApi($http) {
    var EVENTS_PATH = '/api/events';

    this.getEvents = function (done) {
        return $http.get(EVENTS_PATH).then(function (response) {
            done(response.data);
        });
    };
    this.createEvent = function (eventData, done) {
        $http.post(EVENTS_PATH, eventData).then(done());
    };
}

