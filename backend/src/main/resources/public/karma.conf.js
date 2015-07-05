module.exports = function (config) {
    config.set({

        basePath: './',

        files: ['bower_components/angular/angular.js',
            'bower_components/angular-route/angular-route.js',
            'bower_components/angular-mocks/angular-mocks.js',
            //'app/*.js',
            'test/*.js',
            'app/services/*.js',
            'app/components/**/*.js',
            'app/angular/*.js'
        ],

        autoWatch: true,

        frameworks: ['jasmine'],

        browsers: ['Firefox'],

        plugins: [
            'karma-firefox-launcher',
            'karma-jasmine',
            'karma-junit-reporter'
        ],

        junitReporter: {
            outputFile: 'test_out/unit.xml',
            suite: 'unit'
        }

    });
};
