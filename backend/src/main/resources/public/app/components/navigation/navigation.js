'use strict';

function NavigationCtrl(AuthService) {
    AuthService.authenticate();

    this.logout = function () {
        AuthService.logout();
    }
}

