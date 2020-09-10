angular.module('app').controller('editProjectController', function ($scope, $http, $routeParams, $window) {
    const contextPath = 'http://localhost:8189/app';

    getProject = function (projectId) {
        $http.get(contextPath + '/api/v1/projects/' + projectId)
            .then(function (response) {
                $scope.ProjectProperty = response.data;
            });
    };
    getProject($routeParams.projectId);

    $scope.deleteProject = function (projectId){
        $http.delete(contextPath + '/api/v1/projects/' + projectId)
            .then(function () {
                $window.location.href = contextPath + '/index.html#!/projects';
            });
    };
});