'use strict';

describe("Events Controller", function() {

    it("should update list of events", function() {
        var someEvent = 'someEvent';
        var eventsServiceStub = new function() {
            this.getEvents = function(done) {
                done([someEvent]);
            }
        };
        var controller = new EventListCtrl(eventsServiceStub);
        expect(controller.events).toEqual([someEvent]);
    });
});