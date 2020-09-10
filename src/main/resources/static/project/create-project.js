angular.module('app').controller('createProjectController', function ($scope, $http, $window) {
    const contextPath = 'http://localhost:8189/app';
    $scope.newProject = {};

    fillTable = function () {
        $http.get(contextPath + '/api/v1/users/dtos')
            .then(function (response) {
                $scope.users = response.data;
                console.log($scope.users);
            });
    };

    $scope.SubmitCreateNewProject = function (){
        let users = [];
        for (let key in $scope.newProject.users) {
            let temp = {};
            temp.id = $scope.newProject.users[key];
            users.push(temp);
        }
        $scope.newProject.users = users;
        $http.post(contextPath + '/api/v1/projects/create', $scope.newProject)
            .then(function (){
                $window.location.href = contextPath + '/index.html';
            });
    }
    fillTable();

});