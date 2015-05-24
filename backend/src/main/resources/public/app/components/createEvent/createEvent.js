'use strict';

(function () {

    function CreateEventCtrl(Events, $scope, $location) {
        $scope.eventForm = {};
        $scope.createEvent = function(){
            Events.save($scope.eventForm, function() {
                $location.path('/event-list');

            }, function(){
                console.error('-----------> Failed to save a new event :(');
            });

        }
    }

    angular
        .module('goodtimesApp.createEvent', ['goodtimesApp.eventService'])
        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider.when('/create-event', {
                templateUrl: 'components/createEvent/createEvent.html',
                controller: 'CreateEventCtrl'
            });
        }])
        .controller('CreateEventCtrl', CreateEventCtrl);
})();
