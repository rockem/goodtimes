'use strict';

function LoginCtrl(authService, $rootScope, $scope, $location) {
    var username;
    var password;
    this.login = function () {
        authService.authenticate({username: this.username, password:this.password}, function () {
            if ($rootScope.authenticated) {
                $location.path("/");
                $scope.error = false;
            } else {
                $location.path("/login");
                $scope.error = true;
            }
        });
    };
}


