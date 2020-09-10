angular.module('app').controller('createProjectController', function ($scope, $http, $window) {
    const contextPath = 'http://localhost:8189/app';

    fillTable = function () {
        console.log('CONSOLE LOG GET START');
        $http.get(contextPath + '/api/v1/users/dtos')
            .then(function (response) {
                $scope.UsersList = response.data;
                console.log('CONSOLE LOG GET');
                console.log($scope.UsersList);
            });
    };
    fillTable();

    $scope.SubmitCreateNewProject = function (){
        console.log('PPOST START= ');

        console.log($scope.newProject);
        console.log($scope.newUsers);
        let users = [];
        for (let key in $scope.newProject.users){
            let temp = {};
            temp.id= $scope.newProject.users[key];
            users.push(temp);
        }
        $scope.newProject.users = users;
        console.log('NEW PROJECT = ');
        console.log($scope.newProject);
        $http.post(contextPath + '/api/v1/projects/create', $scope.newProject)
            .then(function (){
                $window.location.href = contextPath + '/index.html#!/projects';
            });
    };

});