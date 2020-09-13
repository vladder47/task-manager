angular.module('app').controller('editTaskController', function ($scope, $http, $routeParams, $window, $localStorage) {
    const contextPath = 'http://localhost:8189/app';
    $scope.editTask = {};
    $scope.editTask.project = {};
    $scope.notification = {};
    $scope.notification.users = {};
    $scope.editTask.leader = {};

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
                                console.log($scope.editTask.users);
                                $scope.editTask.id = $scope.task.id;
                                $scope.editTask.title = $scope.task.title;
                                $scope.editTask.leader = {};
                                $scope.editTask.leader.id = $scope.task.leaderId;
                                $scope.editTask.description = $scope.task.description;
                                $scope.editTask.priority = $scope.task.priority;
                                $scope.editTask.status = $scope.task.status;
                                $scope.editTask.project.id = $scope.task.projectId;
                                $scope.notification.text = "Task " + $scope.task.id + " was changed";
                                $scope.currentUser = $localStorage.currentUser;
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
                    users[allUsers[i]['id']] = allUsers[i]['id'];
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
            if ($scope.editTask.users[key] !== false) {
                temp.id = $scope.editTask.users[key];
                users.push(temp);
            }
        }
        $scope.editTask.users = users;
        // в случае, если изменение даты не происходит, то поле считается null,
        // поэтому приходится доставать его вручную
        $scope.editTask.deadLine = new Date(document.getElementById("editTaskDeadline").value);
        $scope.notification.users = $scope.editTask.users;
        $http.put(contextPath + '/api/v1/tasks', $scope.editTask)
            .then(function () {
                console.log($scope.notification.users);
                $http.post(contextPath + '/api/v1/notifications', $scope.notification)
                    .then(function () {
                        $window.location.href = contextPath + '/index.html';
                    });
            });
    };

    fillTable($routeParams.taskId);
});