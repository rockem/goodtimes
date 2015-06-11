'use strict';

// Declare app level module which depends on views, and components
angular
    .module('goodtimesApp', [
        'ngRoute',
        'ngResource',
        'goodtimesApp.navigation',
        'goodtimesApp.createEvent',
        'goodtimesApp.eventList',
        'goodtimesApp.login'])

    .config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {
        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
        $routeProvider
            .when('/', {templateUrl: 'components/home/home.html'})
            .when('/login', {templateUrl: 'components/login/login.html'})
            .when('/event-list', {templateUrl: 'components/eventList/eventList.html'})
            .when('/create-event', {templateUrl: 'components/createEvent/createEvent.html'})
            .otherwise({redirectTo: '/create-event'});
    }]);