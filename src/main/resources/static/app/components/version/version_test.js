'use strict';

describe('goodtimesApp.version module', function() {
  beforeEach(module('goodtimesApp.version'));

  describe('version service', function() {
    it('should return current version', inject(function(version) {
      expect(version).toEqual('0.1');
    }));
  });
});
