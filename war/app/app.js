'use strict';

var myApp = angular.module('mainApp', ['ngRoute', 'ngResource', 'mgcrea.ngStrap']);

myApp.config(function($routeProvider) {
	$routeProvider.when('/register', {
		templateUrl : 'app/views/registration.tpl.html',
		controller : 'registrationController'
	})
	.when('/login', {
		templateUrl : 'app/views/login.tpl.html',
		controller : 'LoginCtrl'
	});
});