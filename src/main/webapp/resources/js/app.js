'use strict';
var GtsDemoApp = {};

var App = angular.module('GtsDemoApp', ['GtsDemoApp.filters', 'GtsDemoApp.services', 'GtsDemoApp.directives']);

// Declare app level module which depends on filters, and services
App.config(['$routeProvider', function ($routeProvider) {

    $routeProvider.when('/companies', {
        templateUrl: 'companies/layout',
        controller: CompanyController
    });
    $routeProvider.otherwise({redirectTo: '/companies'});
}]);
