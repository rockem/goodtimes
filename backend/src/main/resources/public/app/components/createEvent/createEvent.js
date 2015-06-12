'use strict';

function CreateEventCtrl($scope, $location, eventsApi) {
    $scope.eventForm = {};
    $scope.createEvent = function () {
        eventsApi.createEvent($scope.eventForm).then(function (eventData) {
            $location.path('/event-list');
        });
    }
}

