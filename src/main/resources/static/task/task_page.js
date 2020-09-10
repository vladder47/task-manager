angular.module('app').controller('taskPageController', function ($scope, $http, $routeParams) {
    const contextPath = 'http://localhost:8189/app';

    getTask = function (taskId) {
        $http.get(contextPath + '/api/v1/tasks/' + taskId)
            .then(function (response) {
                $scope.task = response.data;
            });
        $http.get(contextPath + '/api/v1/users/dtos/task/' + taskId)
            .then(function (response) {
                let users = '';
                for (let key in response.data) {
                    users += response.data[key]['username'] + '; ';
                }
                $scope.users = users;
            });
        $http.get(contextPath + '/api/v1/comments/' + taskId)
            .then(function (response) {
                $scope.comments = response.data;
            });
    };

    getTask($routeParams.taskId);
});