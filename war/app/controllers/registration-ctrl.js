angular.module('mainApp').controller('registrationController', ['$scope', '$location', 'Customer', function ($scope, $location, Customer) {
	
	$scope.errorMessage = '';
	$scope.successMessage = '';
	
	$scope.register = function(customer){
		console.log(customer);
		$scope.errorMessage = '';
		$scope.successMessage = '';
		
		if(angular.isDefined(customer)) {
			Customer.register(customer).$promise.then(function(result) {
				if(result.isRegistered) {
					$scope.successMessage = customer.email + ' Registered successfully..';
					$location.path('/login');
				} else {
					$scope.errorMessage = customer.email + ' already exists, please choose another one.';
				}
			});
			
		};
	};
	
	$scope.logout = function(){
		User.logout().$promise.then(function(result) {
			console.log("result : " +JSON.stringify(result));
			if(result.isLogout) {
				$location.path('/login');
			} else {
				$scope.errorMessage = 'Logout error.';
			}
		});
	};
	
}]);