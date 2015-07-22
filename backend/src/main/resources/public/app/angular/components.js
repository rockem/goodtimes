'use strict';

angular
    .module('goodtimesApp.login', ['goodtimesApp.authService'])
    .controller('LoginCtrl', LoginCtrl);

angular
    .module('goodtimesApp.eventList', ['goodtimesApp.eventService'])
    .controller('EventListCtrl', EventListCtrl);

angular
    .module('goodtimesApp.createEvent', ['goodtimesApp.eventService'])
    .controller('CreateEventCtrl', CreateEventCtrl);

angular
    .module('goodtimesApp.navigation', ['goodtimesApp.authService'])
    .controller('NavigationCtrl', NavigationCtrl);

angular
    .module('goodtimesApp.signup', [])
    .controller('SignupCtrl', SignupCtrl);