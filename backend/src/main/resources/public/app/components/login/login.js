'use strict';

angular.module('goodtimesApp.login', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/login', {
            templateUrl: 'components/login/login.html',
            controller: 'LoginCtrl'
        });
    }])

    .controller('LoginCtrl', function ($rootScope, $scope, $http, $location) {
        var authenticate = function (user, callback) {
            var headers = user ? {
                authorization: "Basic "
                + btoa(user.username + ":" + user.password)
            } : {};

            $http.get('/api/user', {headers: headers}).success(function (data) {
                $rootScope.authenticated = !!data.name;
                callback && callback();
            }).error(function (error) {
                $rootScope.authenticated = false;
                callback && callback();
            });

        };

        //authenticate();
        $scope.user = {};
        $scope.login = function () {
            authenticate($scope.user, function () {
                if ($rootScope.authenticated) {
                    $location.path("/");
                    $scope.error = false;
                } else {
                    $location.path("/login");
                    $scope.error = true;
                }
            });
        };

    });