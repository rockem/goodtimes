'use strict';

function EventDetailsCtrl(eventsApi, $routeParams) {
    var self = this;
    self.event = {};
    eventsApi.getEvent($routeParams.id, function(data) {
        self.event = data;
    })

}

