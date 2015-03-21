'use strict';

angular.module('goodtimesApp.eventList', [])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/event-list', {
            templateUrl: 'components/eventList/eventList.html',
            controller: 'EventListCtrl'
        });
    }])

    .controller('EventListCtrl', function ($scope) {
        $scope.formData = {};
        $scope.formData.events = events;
    });