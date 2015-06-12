'use strict';

function NavigationCtrl(authService) {
    authService.authenticate();

    this.logout = function () {
        authService.logout();
    }
}

