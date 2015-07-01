'use strict';

describe("EventDetails Controller", function() {

    it('should get a specific event', function() {
        var eventId = '23455';
        var eventDetails = {name: "kuku"};
        var eventsApi = new function() {
            this.getEvent = function(id, callback) {
                this.id = id;
                callback(eventDetails);
            }
        };
        var routeParams = {id: eventId};
        var controller = new EventDetailsCtrl(eventsApi, routeParams);
        expect(controller.event).toEqual(eventDetails);
        expect(eventsApi.id).toEqual(eventId);
    });


});