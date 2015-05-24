'use strict';
(function() {

    function EventsApi($http) {
        this.getEvents = function() {
            return $http.get('/api/events').then(function (response) {
                return response.data;
            });
        };
    }

    angular
        .module('goodtimesApp.eventService', ['ngResource'])
        .factory('EventsApi', function ($http) {
            return new EventsApi($http);
        })
        .factory('Events', function ($resource) {
            return $resource('/api/events', {}, {
                createEvent: function(data) {
                    $resource.save(data);
                }
            });
        });
})();
