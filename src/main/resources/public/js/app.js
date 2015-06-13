(function() {
	
var app = angular.module('bzvApp', ['ngResource', 'spring-data-rest']);

app.config(function (SpringDataRestInterceptorProvider) {
	SpringDataRestInterceptorProvider.apply();
});

app.controller('candidates', function($scope, $http) {
	$http.get('/candidate').
		success(function(data) {console.log(data);
console.log(data._embeddedItems[0]._resources('peasant').get(function(a){console.log(a);})); // FIXME
			$scope.candidates = data._embeddedItems || [];
		});
});

app.controller('peasants', function($scope, $http) {
	$http.get('/peasant').
		success(function(data) {
			$scope.peasants = data._embeddedItems || [];
		});
});

app.controller('votes', function($scope, $http) {
	$http.get('/vote').
		success(function(data) {
			$scope.votes = data._embeddedItems || [];
		});
});

app.controller('votePeriods', function($scope, $http) {
	$http.get('/voteperiod').
		success(function(data) {
			$scope.votePeriods = data._embeddedItems || [];
		});
});

app.controller('voteTypes', function($scope, $http) {
	$http.get('/votetype').
		success(function(data) {
			$scope.voteTypes = data._embeddedItems || [];
		});
});

app.controller('users', function($scope, $http) {
	$http.get('/user').
		success(function(data) {
			$scope.users = data._embeddedItems || [];
		});
});

})();