'use strict';

// Declare app level module which depends on views, and components
angular
    .module('goodtimesApp', [
        'ngRoute',
        'ngResource',
        'goodtimesApp.createEvent',
        'goodtimesApp.eventList',
        'goodtimesApp.login',
        'goodtimesApp.version'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/', {templateUrl: 'components/home/home.html'});
        $routeProvider.when('/login', {templateUrl: 'components/login/login.html'});
        $routeProvider.when('/event-list', {templateUrl: 'components/eventList/eventList.html'});
        $routeProvider.when('/create-event', {templateUrl: 'components/createEvent/createEvent.html'});
        //$routeProvider.otherwise({redirectTo: '/create-event'});
}]);