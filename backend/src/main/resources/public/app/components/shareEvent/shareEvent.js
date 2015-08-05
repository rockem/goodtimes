'use strict';

function ShareEventCtrl(eventsApi, $location, $routeParams) {
    var email;
    this.share = function () {
        eventsApi.shareEvent($routeParams.id, this.email, function() {
            $location.path('/')
        });
    };
}


