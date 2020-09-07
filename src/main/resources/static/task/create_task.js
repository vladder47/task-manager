angular.module('app').controller('createTaskController', function ($scope, $http, $routeParams, $window) {
    const contextPath = 'http://localhost:8189/app';
    $scope.newTask = {};
    $scope.newTask.project = {};
    $scope.newTask.project.id = $routeParams.projectId;

    fillTable = function () {
        $http.get(contextPath + '/api/v1/users/dtos')
            .then(function (response) {
                $scope.users = response.data;
            });
    };

    $scope.createNewTask = function () {
        $http.post(contextPath + '/api/v1/tasks', $scope.newTask)
            .then(function () {
                $window.location.href = contextPath + '/index.html';
            });
    };

    fillTable();
});