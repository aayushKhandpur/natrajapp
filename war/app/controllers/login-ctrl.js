angular.module('mainApp').controller('LoginCtrl', ['$scope', '$rootScope', '$location', 'Customer', function ($scope, $rootScope, $location, Customer) {
	
	$scope.errorMessage = '';
	
	$scope.login = function(customer){
		//console.log(user);
		$scope.errorMessage = '';
		
		Customer.login(customer).$promise.then(function(result) {
			$rootScope.isAuthenticUser = result.isAuthenticUser;
			if($rootScope.isAuthenticUser) {
				$location.path('/home');
			} else {
				$scope.errorMessage = 'Incorrect Username and Password combination.';
			}
		}, function(error) {
			$scope.errorMessage = 'Incorrect Username and Password combination.';
		});
	};
	
}]);