'use strict';

angular.module('goodtimesApp.eventService', ['ngResource'])

    .factory('Events', function ($resource) {
        return $resource('/api/events');
    });