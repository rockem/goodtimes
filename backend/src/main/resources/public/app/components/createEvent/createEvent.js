'use strict';

angular.module('goodtimesApp.createEvent', ['ngRoute', 'ngResource'])

    .factory('Event', function ($resource) {
        return $resource('/api/events');
    })
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/create-event', {
            templateUrl: 'components/createEvent/createEvent.html',
            controller: 'CreateEventCtrl'
        });
    }])

    .controller('CreateEventCtrl', function (Event, $scope, $location) {
        $scope.eventForm = {};
        $scope.createEvent = function(Events, $location){
            Event.save($scope.eventForm).then(function($location) {
                    $location.path('/event-list');

                }, function(){
                    console.error('-----------> Failed to save a new event :(');
                });
        }
    });