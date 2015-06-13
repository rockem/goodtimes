'use strict';

function CreateEventCtrl($location, eventsApi) {
    var event = {};
    this.createEvent = function () {
        eventsApi.createEvent(this.event).then(function () {
            $location.path('/event-list');
        });
    }
}

