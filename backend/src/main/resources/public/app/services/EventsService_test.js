describe('Events Service', function () {

    beforeEach(function() {
        this.httpStub = new HttpStub();
        this.httpStub.shouldBeSuccessful = true;
        this.service = new EventsApi(this.httpStub);
        this.callback = jasmine.createSpy('done');
    });

    it('dispatch callback on http success', function () {
        this.httpStub.successArgs = [{data: ['something']}];
        this.service.getEvents(this.callback);
        expect(this.callback).toHaveBeenCalledWith(['something']);
    });

    it('should post events to eventsApi', function() {
        this.service.createEvent(['someData'], this.callback);
        expect(this.callback).toHaveBeenCalled();
        expect(this.httpStub.data).toEqual(['someData']);
    });

    it('should put share event', function() {
        var email = "e@e.com";
        var eventId = "23234";
        this.service.shareEvent(eventId, email, this.callback);
        expect(this.callback).toHaveBeenCalled();
        expect(this.httpStub.data).toEqual([{email: email}]);
    });

});