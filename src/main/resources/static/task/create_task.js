angular.module('app').controller('createTaskController', function ($scope, $http, $routeParams, $window) {
    const contextPath = 'http://localhost:8189/app';
    $scope.newTask = {};
    $scope.newTask.project = {};
    $scope.newTask.project.id = $routeParams.projectId;
    $scope.newTask.leader = {};

    fillTable = function (projectId) {
        $http.get(contextPath + '/api/v1/users/dtos/project/' + projectId)
            .then(function (response) {
                $scope.users = response.data;
                console.log($scope.users);
            });
        $http.get(contextPath + '/api/v1/tasks/status')
            .then(function (response) {
                $scope.statuses = response.data;
            });
        $http.get(contextPath + '/api/v1/tasks/priority')
            .then(function (response) {
                $scope.priorities = response.data;
            });
        $http.get(contextPath + '/api/v1/users/current')
            .then(function (response) {
                $scope.newTask.leader.id = response.data.id;
            });
    };

    $scope.createNewTask = function () {
        let users = [];
        for (let key in $scope.newTask.users) {
            let temp = {};
            if ($scope.newTask.users[key] !== false) {
                temp.id = $scope.newTask.users[key];
                users.push(temp);
            }
        }
        $scope.newTask.users = users;

        $http.post(contextPath + '/api/v1/tasks', $scope.newTask)
            .then(function () {
                $window.location.href = contextPath + '/index.html';
            });
    };

    fillTable($routeParams.projectId);
});