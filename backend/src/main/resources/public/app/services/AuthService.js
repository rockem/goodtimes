'use strict';

function AuthService($http, $rootScope) {

    this.authenticate = function (credentials, callback) {
        var headers = credentials ? {
            authorization: "Basic "
            + btoa(credentials.username + ":" + credentials.password)
        } : {};

        $http.get('/api/user', {headers: headers}).success(function (data) {
            $rootScope.authenticated = !!data.name;
            callback && callback();
        }).error(function () {
            $rootScope.authenticated = false;
            callback && callback();
        });

    };

    this.logout = function (callback) {
        $http.post('/api/logout', {}).success(function () {
            $rootScope.authenticated = false;
            callback && callback();
        }).error(function () {
            $rootScope.authenticated = false;
            callback && callback();
        });

    }
}