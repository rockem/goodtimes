describe("Login Controller", function () {

    var USERNAME = "kuku";
    var PASSWORD = "1234";

    beforeEach(function() {
        this.authService = new function () {
            this.authenticate = function (credentials, callback) {
                this.credentials = credentials;
                callback && callback()
            }
        };
        this.rootScope = {};
        this.scope = {};
        this.location = new Location();
        this.ctrl = new LoginCtrl(this.authService, this.rootScope, this.scope, this.location);
    });

    it("should delegate username and password", function () {
        this.ctrl.username = USERNAME;
        this.ctrl.password = PASSWORD;
        this.ctrl.login();
        expect(this.authService.credentials).toMatch(
            {username: this.USERNAME, password: this.PASSWORD});
    });

    it("should redirect to /login with error if authentication failed", function () {
        this.rootScope.authenticated = false;
        this.ctrl.login();
        expect(this.scope.error).toEqual(true);
        expect(this.location.path).toHaveBeenCalledWith('/login');
    });

    it("should redirect to / if authentication succeed", function () {
        this.rootScope.authenticated = true;
        this.ctrl.login();
        expect(this.scope.error).toEqual(false);
        expect(this.location.path).toHaveBeenCalledWith('/');
    })

});
