'use strict';

(function () {

    function NavigationCtrl(AuthService, $scope) {
        AuthService.authenticate();

        this.logout = function() {
            AuthService.logout();
        }
    }

    angular
        .module('goodtimesApp.navigation', ['goodtimesApp.authService'])
        .controller('NavigationCtrl', NavigationCtrl)

}) ();