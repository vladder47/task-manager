angular.module('app').controller('taskPageController', function ($scope, $http, $routeParams) {
    const contextPath = 'http://localhost:8189/app';
    $scope.newComment = {};
    $scope.newComment.task = {};

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
            });
    };

    $scope.sendComment = function() {
        $scope.newComment.task.id = $routeParams.taskId;
        var e = document.getElementById("commentUsername");
        $scope.newComment.user.username = e.options[e.selectedIndex].text;
        $scope.newComment.parent = 0;
        $http.post(contextPath + '/api/v1/comments', $scope.newComment)
            .then(function () {
                getTask($routeParams.taskId);
            });
    };

    getTask($routeParams.taskId);
});