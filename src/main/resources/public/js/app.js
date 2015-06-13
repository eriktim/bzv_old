(function() {
	
var app = angular.module('bzvApp', []);

app.controller('candidates', function($scope, $http) {
	$http.get('/candidate').
		success(function(data) {
			$scope.candidates = data._embedded.candidate || [];
		});
});

app.controller('peasants', function($scope, $http) {
	$http.get('/peasant').
		success(function(data) {
			$scope.peasants = data._embedded.peasant || [];
		});
});

app.controller('votes', function($scope, $http) {
	$http.get('/vote').
		success(function(data) {
			$scope.votes = data._embedded.vote || [];
		});
});

app.controller('votePeriods', function($scope, $http) {
	$http.get('/voteperiod').
		success(function(data) {
			$scope.votePeriods = data._embedded.voteperiod || [];
		});
});

app.controller('voteTypes', function($scope, $http) {
	$http.get('/votetype').
		success(function(data) {
			$scope.voteTypes = data._embedded.votetype || [];
		});
});

app.controller('users', function($scope, $http) {
	$http.get('/user').
		success(function(data) {
			$scope.users = data._embedded.user || [];
		});
});

})();