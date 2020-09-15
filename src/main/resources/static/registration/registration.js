angular.module('app').controller('registrationController', function ($scope, $http, $routeParams, $window) {
    const contextPath = 'http://localhost:8189/app';
    $scope.newUser= {};

    $scope.createNewUser = function () {
        if ($scope.newUser.password !== $scope.passwordConfirm) {
            alert('Пароли не совпадают!');
        } else {
            $http.post(contextPath + '/api/v1/registration', $scope.newUser)
                .then(function () {
                    $window.location.href = contextPath + '/index.html';
                });
        }
    };
});