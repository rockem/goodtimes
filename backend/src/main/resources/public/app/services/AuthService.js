'use strict';


function AuthService($http, $rootScope) {

    this.authenticate = function (credentials, callback) {
        var headers = credentials ? {
            authorization: "Basic "
            + btoa(credentials.username + ":" + credentials.password)
        } : {};

        $http.get('/api/user', {headers: headers}).then(function (response) {
            $rootScope.authenticated = !!response.data.name;
            callback && callback();
        }, function () {
            $rootScope.authenticated = false;
            callback && callback();
        });

    };

    this.logout = function (callback) {
        $http.post('/api/logout', {}).then(function () {
            $rootScope.authenticated = false;
            callback && callback();
        }, function () {
            $rootScope.authenticated = false;
            callback && callback();
        });

    }
}