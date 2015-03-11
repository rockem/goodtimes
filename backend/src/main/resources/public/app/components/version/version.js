'use strict';

angular.module('goodtimesApp.version', [
  'goodtimesApp.version.interpolate-filter',
  'goodtimesApp.version.version-directive'
])

.value('version', '0.1');
