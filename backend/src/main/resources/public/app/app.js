'use strict';

// Declare app level module which depends on views, and components
angular.module('goodtimesApp', [
    'ngRoute',
    'ngResource',
    'goodtimesApp.createEvent',
    'goodtimesApp.eventList',
    'goodtimesApp.login',
    'goodtimesApp.version'
]).
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.otherwise({redirectTo: '/login'});
    }]);
