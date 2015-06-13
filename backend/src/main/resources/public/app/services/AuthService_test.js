describe('Auth Service', function () {

    var USERNAME = "kuku";
    var PASSWORD = "1234";

    beforeEach(function() {
        this.httpStub = new HttpStub();
        this.rootScope = {};
        this.service = new AuthService(this.httpStub, this.rootScope);
    });

    it('delegate credentials as header', function () {
        this.service.authenticate({username: USERNAME, password: PASSWORD});
        expect(this.httpStub.headers).toMatch({authorization: "Basic " + btoa(USERNAME + ":" + PASSWORD)});
    });

    describe('Client authentication', function() {

        it('authenticated if logged in', function () {
            this.httpStub.shouldBeSuccessful = true;
            this.httpStub.successArgs = [{data: {name: "kuku"}}];
            this.service.authenticate({});
            expect(this.rootScope.authenticated).toBe(true);
        });

        it('not authenticated if http error', function () {
            this.httpStub.shouldBeSuccessful = false;
            this.service.authenticate({});
            expect(this.rootScope.authenticated).toBe(false);
        });
    });

    describe('Dispatch callback on login', function() {

        beforeEach(function() {
            this.callback = mockFunction();

        });

        it('dispatch call back on http success', function () {
            this.httpStub.shouldBeSuccessful = true;
            this.httpStub.successArgs = [{data: {name: ""}}];
            this.service.authenticate({}, this.callback);
            verify(this.callback)();
        });

        it('dispatch call back on http error', function () {
            this.httpStub.shouldBeSuccessful = false;
            this.service.authenticate({}, this.callback);
            verify(this.callback)();
        });
    });

    describe('Logout', function() {

        beforeEach(function() {
            this.callback = mockFunction();
        });

        it('on http success', function() {
            this.httpStub.shouldBeSuccessful = true;
            this.service.logout(this.callback);
        });

        it('on http error', function() {
            this.httpStub.shouldBeSuccessful = false;
            this.service.logout(this.callback);
        });

        afterEach(function() {
            expect(this.rootScope.authenticated).toBe(false);
            verify(this.callback)();
        })
    });

});