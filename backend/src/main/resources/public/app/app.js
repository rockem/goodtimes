'use strict';

// Declare app level module which depends on views, and components
angular.module('goodtimesApp', [
  'ngRoute',
  'ngResource',
  'goodtimesApp.createEvent',
  'goodtimesApp.eventList',
  'goodtimesApp.version'
]).
config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/event-list', {
            templateUrl: 'components/eventList/eventList.html'
        });
        $routeProvider.otherwise({redirectTo: '/create-event'});
}]);
