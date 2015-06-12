'use strict';

function CreateEventCtrl($scope, $location, EventsApi) {
    $scope.eventForm = {};
    $scope.createEvent = function () {
        EventsApi.createEvent($scope.eventForm).then(function (eventData) {
            $location.path('/event-list');
        });
    }
}

