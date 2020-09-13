angular.module('app').controller('editProjectController', function ($scope, $http, $routeParams, $window) {
    const contextPath = 'http://localhost:8189/app';
    $scope.editProject = {};
    $scope.editProject.leader = {};

    fillTable = function (projectId) {
        $http.get(contextPath + '/api/v1/projects/' + projectId)
            .then(function (response) {
                $scope.project = response.data;
                $http.get(contextPath + '/api/v1/users/dtos')
                    .then(function (response) {
                        $scope.users = response.data;
                        $http.get(contextPath + '/api/v1/users/dtos/project/' + projectId)
                            .then(function (response) {
                                $scope.projectUsers = response.data;
                                $scope.editProject.id = $scope.project.id;
                                $scope.editProject.title = $scope.project.title;
                                $scope.editProject.leader.id = $scope.project.leaderId;
                                $scope.editProject.users = setUsers($scope.projectUsers, $scope.users);
                                console.log($scope.editProject);
                            });
                    });
            });
    };

    // функция, которая формирует массив editProject.users
    // сделана для того, чтобы первоначально были выделены исполнители проекта
    setUsers = function(projectUsers, allUsers) {
        let users = {};
        for (let i in allUsers) {
            for (let j in projectUsers) {
                if (allUsers[i]['id'] === projectUsers[j]['id'] && allUsers[i]['username'] === projectUsers[j]['username']) {
                    users[parseInt(i) + 1] = true;
                    break;
                }
            }
        }
        return users;
    }

    $scope.modifyProject = function () {
        let users = [];
        for (let key in $scope.editProject.users) {
            let temp = {};
            if ($scope.editProject.users[key] === true) {
                let id = $scope.users[key - 1]['id'];
                temp.id = id;
                users.push(temp);
            }
        }
        $scope.editProject.users = users;
        // в случае, если изменение даты не происходит, то поле считается null,
        // поэтому приходится доставать его вручную
        $scope.editProject.deadline = new Date(document.getElementById("editProjectDeadline").value);
        $http.put(contextPath + '/api/v1/projects', $scope.editProject)
            .then(function () {
                $window.location.href = contextPath + '/index.html';
            });
    };

    fillTable($routeParams.projectId);

});