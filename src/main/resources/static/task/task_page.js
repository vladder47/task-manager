angular.module('app').controller('taskPageController', function ($scope, $http, $routeParams, $localStorage) {
    const contextPath = 'http://localhost:8189/app';
    $scope.newComment = {};
    $scope.newComment.task = {};
    $scope.newComment.user = {};

    getTask = function (taskId) {
        $http.get(contextPath + '/api/v1/tasks/' + taskId)
            .then(function (response) {
                $scope.task = response.data;
            });
        $http.get(contextPath + '/api/v1/users/dtos/task/' + taskId)
            .then(function (response) {
                $scope.senders = response.data;
                let users = '';
                for (let key in response.data) {
                    users += response.data[key]['username'] + '; ';
                }
                $scope.users = users;
            });
        $http.get(contextPath + '/api/v1/comments/' + taskId)
            .then(function (response) {
                $scope.comments = response.data;
                $scope.currentUser = $localStorage.currentUser;
            });
        $http.get(contextPath + '/api/v1/users/current')
            .then(function (response) {
                $scope.newComment.user.id = response.data.id;
            });
    };

    $scope.sendComment = function() {
        $scope.newComment.task.id = $routeParams.taskId;
        $scope.newComment.parent = 0;
        $http.post(contextPath + '/api/v1/comments', $scope.newComment)
            .then(function () {
                getTask($routeParams.taskId);
                $scope.newComment.text = null;
            });
    };

    getTask($routeParams.taskId);
});