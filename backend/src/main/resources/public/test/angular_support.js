'use strict';

function HttpStub() {
    var url;
    var headers;
    var shouldBeSuccessful = false;

    var successArgs = [];
    var errorArgs = [];


    this.httpAction = function(url, headers) {
        this.url = url;
        this.headers = headers;
        return new this.httpPromise(
            this.shouldBeSuccessful,
            this.successArgs,
            this.errorArgs);
    };

    this.get = this.httpAction;
    this.post = this.httpAction;

    this.httpPromise = function (success, successArgs, errorArgs) {
        this.then = function (successCallback, failureCallback) {
            if (success) {
                successCallback && successCallback.apply(this, successArgs);
            } else {
                failureCallback && failureCallback.apply(this, errorArgs);
            }
        };
    };
}