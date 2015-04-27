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

    .controller('CreateEventCtrl', function (Events, $scope, $location) {
        $scope.eventForm = {};
        $scope.createEvent = function(){
            Events.save($scope.eventForm, function() {
                    $location.path('/event-list');

                }, function(){
                    console.error('-----------> Failed to save a new event :(');
                });
        }
    });