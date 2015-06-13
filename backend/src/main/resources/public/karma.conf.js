module.exports = function (config) {
    config.set({

        basePath: './',

        files: ['app/bower_components/angular/angular.js',
            'app/bower_components/angular-route/angular-route.js',
            'app/bower_components/angular-mocks/angular-mocks.js',
            //'app/*.js',
            'app/services/*.js',
            'app/components/**/*.js',
            'app/angular/*.js'
        ],

        autoWatch: true,

        frameworks: ['jasmine', 'jsmockito-jshamcrest'],

        browsers: ['Chrome'],

        plugins: [
            'karma-chrome-launcher',
            'karma-jasmine',
            'karma-junit-reporter',
            'karma-jsmockito-jshamcrest'
        ],

        junitReporter: {
            outputFile: 'test_out/unit.xml',
            suite: 'unit'
        }

    });
};
