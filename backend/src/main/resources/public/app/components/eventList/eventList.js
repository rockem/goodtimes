'use strict';

angular.module('goodtimesApp.eventList', ['goodtimesApp.eventService', 'ngRoute','ngResource'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/event-list', {
            templateUrl: 'components/eventList/eventList.html',
            controller: 'EventListCtrl'
        });
    }])

    .controller('EventListCtrl', function ($scope, Events) {
         $scope.events = Events.query();
    });