angular.module('mainApp').controller('headerController', function ($scope, $location) {
	
	$scope.isActiveTab = function(route) {
	    return route === $location.path();
	};
	
});