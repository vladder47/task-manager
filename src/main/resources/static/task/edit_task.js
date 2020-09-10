angular.module('app').controller('editTaskController', function ($scope, $http, $routeParams, $window) {
    const contextPath = 'http://localhost:8189/app';
    $scope.editTask = {};
    $scope.editTask.project = {};

    fillTable = function (taskId) {
        $http.get(contextPath + '/api/v1/tasks/' + taskId)
            .then(function (response) {
                $scope.task = response.data;
                $http.get(contextPath + '/api/v1/users/dtos/project/' + $scope.task.projectId)
                    .then(function (response) {
                        $scope.users = response.data;
                        $http.get(contextPath + '/api/v1/users/dtos/task/' + taskId)
                            .then(function (response) {
                                $scope.taskUsers = response.data;
                                $scope.editTask.users = setUsers($scope.taskUsers, $scope.users);
                                $scope.editTask.id = $scope.task.id;
                                $scope.editTask.title = $scope.task.title;
                                $scope.editTask.leader = {};
                                $scope.editTask.leader.id = $scope.task.leaderId;
                                $scope.editTask.description = $scope.task.description;
                                $scope.editTask.priority = $scope.task.priority;
                                $scope.editTask.status = $scope.task.status;
                                $scope.editTask.project.id = $scope.task.projectId;
                            });
                    });
            });
        $http.get(contextPath + '/api/v1/tasks/status')
            .then(function (response) {
                $scope.statuses = response.data;
            });
        $http.get(contextPath + '/api/v1/tasks/priority')
            .then(function (response) {
                $scope.priorities = response.data;
            });
    };

    // функция, которая формирует массив editTask.users
    // сделана для того, чтобы первоначально были выделены исполнители задачи
    setUsers = function(taskUsers, allUsers) {
        let users = {};
        for (let i in allUsers) {
            for (let j in taskUsers) {
                if (allUsers[i]['id'] === taskUsers[j]['id'] && allUsers[i]['username'] === taskUsers[j]['username']) {
                    users[parseInt(i) + 1] = true;
                    break;
                }
            }
        }
        return users;
    }

    $scope.modifyTask = function () {
        let users = [];
        for (let key in $scope.editTask.users) {
            let temp = {};
            if ($scope.editTask.users[key] === true) {
                let id = $scope.users[key - 1]['id'];
                temp.id = id;
                users.push(temp);
            }
        }
        $scope.editTask.users = users;
        // в случае, если изменение даты не происходит, то поле считается null,
        // поэтому приходится доставать его вручную
        $scope.editTask.deadLine = new Date(document.getElementById("editTaskDeadline").value);
        $http.put(contextPath + '/api/v1/tasks', $scope.editTask)
            .then(function () {
                $window.location.href = contextPath + '/index.html';
            });
    };

    fillTable($routeParams.taskId);
});