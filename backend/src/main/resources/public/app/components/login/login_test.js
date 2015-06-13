describe("Login Controller", function () {

    var USERNAME = "kuku";
    var PASSWORD = "1234";

    var authService, rootScope, scope, location;
    var ctrl;

    beforeEach(function() {
        this.authService = new function () {
            var credentials;
            this.authenticate = function (credentials, callback) {
                this.credentials = credentials;
                callback && callback()
            }
        };
        this.rootScope = {};
        this.scope = {};
        this.location = new function() {
            this.path = mockFunction()
        };
        this.ctrl = new LoginCtrl(this.authService, this.rootScope, this.scope, this.location);
    });

    it("should delegate username and password", function () {
        this.ctrl.username = USERNAME;
        this.ctrl.password = PASSWORD;
        this.ctrl.login();
        expect(this.authService.credentials).toMatch({username: this.USERNAME, password: this.PASSWORD});
    });

    it("should redirect to /login with error if authentication failed", function () {
        this.rootScope.authenticated = false;
        this.ctrl.login();
        expect(this.scope.error).toEqual(true);
        verify(this.location.path)('/login');
    });

    it("should redirect to / if authentication succeed", function () {
        this.rootScope.authenticated = true;
        this.ctrl.login();
        expect(this.scope.error).toEqual(false);
        verify(this.location.path)('/');
    })

});
