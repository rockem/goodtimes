'use strict';

// Declare app level module which depends on views, and components
angular
    .module('goodtimesApp', [
        'ngRoute',
        'goodtimesApp.eventService',
        'goodtimesApp.createEvent',
        'goodtimesApp.eventList',
        'goodtimesApp.version'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/event-list', {templateUrl: 'components/eventList/eventList.html'});
        $routeProvider.when('/create-event', {templateUrl: 'components/createEvent/createEvent.html'});
        $routeProvider.otherwise({redirectTo: '/create-event'});
}]);
