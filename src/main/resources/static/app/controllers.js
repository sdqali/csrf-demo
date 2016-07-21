(function(angular) {
  var LoginController = function($scope, $localStorage, $http, $location, Login) {
    $scope.login = function(username, password) {
      new Login({username: username, password: password},
          function (data, headers) {
            $localStorage.user = data.user;
            $localStorage.authToken = headers['x-auth-token'];
            $http.defaults.headers.common['x-auth-token'] = headers['x-auth-token'];
            $location.path("/");
          }, function (error) {
            console.log(error);
          });
    };

    $scope.logout = function () {
      delete $localStorage.user;
      delete $localStorage.authToken;
      $http.defaults.headers.common = {};
    }

    $scope.logout();
  };

  LoginController.$inject = ['$scope', '$localStorage', '$http', '$location','Login'];
  angular.module("jwtDemo.controllers").controller("LoginController", LoginController);


  var ProfileController = function ($scope, $localStorage, Hello) {
    $scope.profile = $localStorage.user;
    new Hello().$hello(function (resp, headers) {
      $scope.greeting = resp.message;
    }, function (error) {
      console.log(error);
    })
  };
  ProfileController.inject = ['$scope', '$localStorage', 'Hello'];
  angular.module("jwtDemo.controllers").controller("ProfileController", ProfileController);
}(angular));