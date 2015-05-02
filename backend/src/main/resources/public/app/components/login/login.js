'use strict';

angular.module('goodtimesApp.login', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/login', {
            templateUrl: 'components/login/login.html',
            controller: 'LoginCtrl'
        });
    }])

    .controller('LoginCtrl', function ($scope) {

    });