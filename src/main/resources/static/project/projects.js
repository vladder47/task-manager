angular.module('app').controller('projectsController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app';

    fillTable = function () {
        $http.get(contextPath + '/api/v1/projects')
            .then(function (response) {
                $scope.ProjectsList = response.data;
            });
    };

    fillTable();
});