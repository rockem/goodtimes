describe("Navigation Controller", function() {


    it("should authenticate", function() {
        var authServiceMock = createSpyOn(AuthService);
        new NavigationCtrl(authServiceMock);
        expect(authServiceMock.authenticate).toHaveBeenCalled();
    })
});