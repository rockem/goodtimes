'use strict';

function LoginCtrl($rootScope, $scope, authService, $location) {
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


