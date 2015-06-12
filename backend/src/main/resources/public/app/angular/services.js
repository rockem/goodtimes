'use strict';

angular
    .module('goodtimesApp.authService', [])
    .factory('AuthService', function ($http, $rootScope) {
        return new AuthService($http, $rootScope);
    });

angular
    .module('goodtimesApp.eventService', [])
    .factory('EventsApi', function ($http) {
        return new EventsApi($http);
    });