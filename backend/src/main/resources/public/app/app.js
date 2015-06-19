'use strict';

// Declare app level module which depends on views, and components
angular
    .module('goodtimesApp', [
        'ngRoute',
        'goodtimesApp.navigation',
        'goodtimesApp.eventService',
        'goodtimesApp.createEvent',
        'goodtimesApp.eventList',
        'goodtimesApp.login'])

    .config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {
        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
        $routeProvider
            .when('/', {templateUrl: 'app/components/home/home.html'})
            .when('/login', {templateUrl: 'app/components/login/login.html'})
            .when('/event-list', {templateUrl: 'app/components/eventList/eventList.html'})
            .when('/create-event', {templateUrl: 'app/components/createEvent/createEvent.html'})
            .otherwise({redirectTo: '/'});
    }]);