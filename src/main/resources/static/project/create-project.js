angular.module('app').controller('createProjectController', function ($scope, $http, $log) {
    const contextPath = 'http://localhost:8189/app';

    fillTable = function () {
        $http.get(contextPath + '/api/v1/users')
            .then(function (response) {
                $scope.LeaderList = response.data;
                console.log('CONSOLE LOG GET');
                console.log($scope.LeaderList);
            });
    };

    $scope.SubmitCreateNewProject = function (){
        console.log('CONSOLE LOG POST');
        console.log($scope.newProject);
        console.log('CONSOLE LOG POST NEW USER');
        console.log($scope.newUsers);
        $http.post(contextPath + '/api/v1/projects/create', $scope.newProject)
            .then(function (response){
                $scope.push(response.data);
            });
    }
    fillTable();

});