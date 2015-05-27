'use strict';

angular.module('goodtimesApp.login', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/login', {
            templateUrl: 'components/login/login.html',
            controller: 'LoginCtrl'
        });
    }])

    .controller('LoginCtrl', function ($scope, $http) {

        $scope.user = {};
        $scope.login = function() {
            authenticate($scope.user, function() {
                if ($rootScope.authenticated) {
                    $location.path("/");
                    alert("login");
                    $scope.error = false;
                } else {
                    $location.path("/login");
                    $scope.error = true;
                    alert("Error login");
                }
            });
        };

        var authenticate = function(user, callback) {
            var headers = user ? {authorization : "Basic "
            + btoa(user.username + ":" + user.password)
            } : {};

            $http.get('/user', {headers : headers}).success(function(data) {
                alert("authenticated success");
                $rootScope.authenticated = !!data.name;
                callback && callback();
            }).error(function(error) {
                alert("authenticated failure".concat(error));
                $rootScope.authenticated = false;
                callback && callback();
            });

        };


    });