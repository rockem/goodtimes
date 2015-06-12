'use strict';

function EventListCtrl($scope, EventsApi) {
    $scope.events = [];
    EventsApi.getEvents().then(function (data) {
        angular.copy(data, $scope.events);
    });
}

