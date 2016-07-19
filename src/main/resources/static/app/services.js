(function(angular) {
  var LoginFactory = function($http) {
    return function(credentials, success, error) {
      $http({
        method: 'POST',
        url: '/login',
        headers: {
          Authorization: "Basic " + btoa(credentials.username + ":" + credentials.password)
        }
      }).then(function (resp) {
        success(resp.data, resp.headers())
      }, error);
    };
  };

  LoginFactory.$inject = ['$http'];
  angular.module('jwtDemo.services').factory('Login', LoginFactory);

  var HelloFactory = function($resource) {
    return $resource('/hello', {}, {
      hello: {
        method: 'GET'
      }
    });
  };

  HelloFactory.$inject = ['$resource'];
  angular.module('jwtDemo.services').factory('Hello', HelloFactory);}(angular));