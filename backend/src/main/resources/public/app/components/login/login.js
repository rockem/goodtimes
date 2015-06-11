'use strict';

(function () {

    function LoginCtrl($rootScope, $scope, AuthService, $location) {
        $scope.user = {};
        this.login = function () {
            AuthService.authenticate($scope.user, function () {
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

    angular
        .module('goodtimesApp.login', ['goodtimesApp.authService'])
        .controller('LoginCtrl', LoginCtrl);
})();
