'use strict';

angular.module('goodtimesApp.createEvent', ['ngRoute', 'ngResource'])

    .factory('Events', function ($resource) {
        return $resource('/api/events');
    })
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/create-event', {
            templateUrl: 'components/createEvent/createEvent.html',
            controller: 'CreateEventCtrl'
        });
    }])

    .controller('CreateEventCtrl', function ($scope, $location, Events) {
        $scope.eventForm = {};
        $scope.createEvent = function () {
            Events.save($scope.eventForm, function () {
                console.error("WTF?????");
                //$location.path('/event-list');
                //if(!$scope.$$phase) $scope.$apply()
            });
        }
    });