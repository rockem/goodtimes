'use strict';

(function () {

    function CreateEventCtrl($scope, $location, EventsApi) {
        $scope.eventForm = {};
        $scope.createEvent = function(){
            EventsApi.createEvent($scope.eventForm).then(function(eventData) {
                $location.path('/event-list');
            });
        }
    }

    angular
        .module('goodtimesApp.createEvent', ['goodtimesApp.eventService'])
        .controller('CreateEventCtrl', CreateEventCtrl);
})();
