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
        this.location = new function() {
            this.path = jasmine.createSpy('path')
        };
        this.ctrl = new SignupCtrl(this.authService, this.rootScope, this.scope, this.location);
    });

    it("should delegate username and password", function () {
        this.ctrl.username = USERNAME;
        this.ctrl.password = PASSWORD;
        this.ctrl.signup();
        expect(this.authService.credentials).toMatch(
            {username: this.USERNAME, password: this.PASSWORD});
    });

    it("should redirect to / if authentication succeed", function () {
        this.rootScope.authenticated = true;
        this.ctrl.signup();
        expect(this.scope.error).toEqual(false);
        expect(this.location.path).toHaveBeenCalledWith('/');
    })

});
