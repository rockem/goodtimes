'use strict';

function SignupCtrl(authService, $rootScope, $scope, $location) {
    var username;
    var password;
    this.signup = function () {
        authService.authenticate({username: this.username, password:this.password}, function () {
            if ($rootScope.authenticated) {
                $location.path("/");
                $scope.error = false;
            } else {
                $location.path("/signup");
                $scope.error = true;
            }
        });
    };
}


