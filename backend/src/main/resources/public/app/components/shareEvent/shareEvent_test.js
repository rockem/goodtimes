describe("ShareEventCtrl", function () {

    var EVENT_ID = "234";
    var EMAIL = "e@e.com";

    beforeEach(function() {
        this.routeParams = {id: EVENT_ID};
        this.location = new Location();

        this.shareEvent = function(controller) {
            controller.email = EMAIL;
            controller.share();
        }
    });

    it("should delegate share event", function() {
        var eventsServiceStub = createSpyOn(EventsApi);
        var controller = new ShareEventCtrl(eventsServiceStub, this.location, this.routeParams);
        this.shareEvent(controller);
        expect(eventsServiceStub.shareEvent).toHaveBeenCalledWith(EVENT_ID, EMAIL, jasmine.any(Function))
    });

    it("should redirect to home page", function() {
        var eventsServiceStub = new function() {
            this.shareEvent = function(eventId, email, callback) {
                callback();
            }
        };
        var controller = new ShareEventCtrl(eventsServiceStub, this.location, this.routeParams);
        this.shareEvent(controller);
        expect(this.location.path).toHaveBeenCalledWith('/');
    });

});

