'use strict';

angular.module('goodtimesApp.createEvent', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/create-event', {
            templateUrl: 'components/createEvent/createEvent.html',
            controller: 'CreateEventCtrl'
        });
    }])

    .controller('CreateEventCtrl', function ($scope) {
        $scope.eventForm = {};
        $scope.eventForm.name = "Eli";
    });