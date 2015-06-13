'use strict';

function createSpyOn(clazz) {
    return new function() {
        for (var methodName in new clazz()) {
            this[methodName] = jasmine.createSpy(this, methodName);
        }
    };
}