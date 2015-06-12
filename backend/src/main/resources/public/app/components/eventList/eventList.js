'use strict';

function EventListCtrl($scope, eventsApi) {
    $scope.events = [];
    eventsApi.getEvents().then(function (data) {
        angular.copy(data, $scope.events);
    });
}

