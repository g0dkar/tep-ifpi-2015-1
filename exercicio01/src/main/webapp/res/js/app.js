(function () {
	var app = angular.module("tepEx1", ["ngMaterial"]);
	app.controller("MainController", ["$scope", "$mdSidenav", function ($scope, $mdSidenav) {
		$scope.toggleSidenav = function (menuId) {
			$mdSidenav(menuId).toggle();
		};
		
		
	}]);
})(angular, window);