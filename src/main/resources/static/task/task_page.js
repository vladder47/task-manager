angular.module('app').controller('taskPageController', function ($scope, $http, $routeParams) {
    const contextPath = 'http://localhost:8189/app';

    getTask = function (taskId) {
        $http.get(contextPath + '/api/v1/tasks/' + taskId)
            .then(function (response) {
                $scope.task = response.data;
            });
    };

    getTask($routeParams.taskId);
});