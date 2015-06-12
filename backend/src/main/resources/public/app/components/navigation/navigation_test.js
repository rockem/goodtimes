describe("Navigation Controller", function() {


    it("should authenticate", function() {
        var authServiceMock = mock(AuthService);
        var controller = new NavigationCtrl(authServiceMock);
        verify(authServiceMock).authenticate();
    })
});