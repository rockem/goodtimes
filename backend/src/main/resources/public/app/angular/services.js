'use strict';

angular
    .module('goodtimesApp.authService', [])
    .factory('authService', function ($http, $rootScope) {
        return new AuthService($http, $rootScope);
    });

angular
    .module('goodtimesApp.eventService', [])
    .factory('eventsApi', function ($http) {
        return new EventsApi($http);
    });