'use strict';

// Declare app level module which depends on views, and components
angular.module('goodtimesApp', [
  'ngRoute',
  'goodtimesApp.createEvent',
  'goodtimesApp.version'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.otherwise({redirectTo: '/create-event'});
}]);
