'use strict';
(function () {

    function EventListCtrl($scope, EventsApi) {
        $scope.events = [];
        EventsApi.getEvents().then(function(data) {
            angular.copy(data, $scope.events);
        });
    }

    angular
        .module('goodtimesApp.eventList', [])
        .controller('EventListCtrl', EventListCtrl);
})();
