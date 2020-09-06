angular.module('app').controller('createTaskController', function ($scope, $http, $routeParams, $window) {
    const contextPath = 'http://localhost:8189/app';

    fillTable = function (projectId) {
        $http.get(contextPath + '/api/v1/tasks/create/' + projectId)
            .then(function (response) {
                $scope.project = response.data;
            });
    };

    $scope.createNewTask = function () {
        $http.post(contextPath + '/api/v1/tasks', $scope.newTask)
            .then(function () {
                $window.location.href = contextPath + '/index.html';
            });
    };

    fillTable($routeParams.projectId);
});