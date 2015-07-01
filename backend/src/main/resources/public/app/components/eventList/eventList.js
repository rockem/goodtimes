'use strict';

function EventListCtrl(eventsApi) {
    var self = this;
    self.events = [];
    eventsApi.getEvents(function (data) {
        self.events = data.slice();
    });
}

