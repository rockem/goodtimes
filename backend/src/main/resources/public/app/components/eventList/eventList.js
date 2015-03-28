'use strict';

angular.module('goodtimesApp.eventList', ['ngRoute','ngResource'])

    .factory("Events", function ($resource) {
        return $resource("/api/events");
    })

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/event-list', {
            templateUrl: 'components/eventList/eventList.html',
            controller: 'EventListCtrl'
        });
    }])

    .controller('EventListCtrl', function ($scope, Events) {
         $scope.events = Events.query();
    });