'use strict';
(function() {
    var URL = '/api/events';

    function EventsApi($http) {
        this.getEvents = function() {
            return $http.get(URL).then(function (response) {
                return response.data;
            });
        };
        this.createEvent = function(eventData) {
            return $http.post(URL, eventData);
        };
    }

    angular
        .module('goodtimesApp.eventService', [])
        .factory('EventsApi', function ($http) {return new EventsApi($http);});
})();
