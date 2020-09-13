angular.module('app').controller('createProjectController', function ($scope, $http, $window) {
    const contextPath = 'http://localhost:8189/app';
    $scope.newProject = {};
    $scope.newProject.leader = {};

    fillTable = function () {
        $http.get(contextPath + '/api/v1/users/dtos')
            .then(function (response) {
                $scope.users = response.data;
            });
        $http.get(contextPath + '/api/v1/users/current')
            .then(function (response) {
                $scope.newProject.leader.id = response.data.id;
            });
    };
    fillTable();

    $scope.SubmitCreateNewProject = function (){
        let users = [];
        for (let key in $scope.newProject.users) {
            let temp = {};
            if ($scope.newProject.users[key] === true) {
                temp.id = key;
                users.push(temp);
            }
        }
        $scope.newProject.users = users;
        $http.post(contextPath + '/api/v1/projects/create', $scope.newProject)
            .then(function (){
                $window.location.href = contextPath + '/index.html';
            });
    };

});