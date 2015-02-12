angular.module('mainApp').factory('Customer', function ($resource, $location) {
	
	var userResource = $resource('/customer/:operation',{
		operation: '@operation'
	}, {
		login : {
			method: 'POST',
			params: {
				operation: 'login'
			}
		},
		getUserInfo : {
			method: 'GET',
			params: {
				operation: 'info'
			},
			interceptor: {
                
                responseError: function (data) {
                    if(data.status == 401) {
                    	$location.path('/login');
                    };
                }
            }
		},
		checkUsername : {
			method: 'GET',
			interceptor: {
                
                responseError: function (data) {
                    if(data.status == 401) {
                    	$location.path('/login');
                    };
                }
            }
		},
		register : {
			method: 'POST',
			params: {
				operation: 'createCustomer'
			},
			interceptor: {
                responseError: function (data) {
                    if(data.status == 401) {
                    	$location.path('/login');
                    };
                }
            }
		},
		logout : {
			method: 'GET',
			params: {
				operation: 'logout'
			}
		}
	});
	
	return userResource;
});