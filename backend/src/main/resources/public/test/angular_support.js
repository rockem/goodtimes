'use strict';

function HttpStub() {
    this.httpAction = function(url, headers) {
        this.url = url;
        this.headers = headers;
        return new this.httpPromise(
            this.shouldBeSuccessful,
            this.successArgs,
            this.errorArgs);
    };

    this.get = this.httpAction;
    this.post = function(url, data, headers) {
        this.data = data;
        return this.httpAction(url, headers);
    };

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