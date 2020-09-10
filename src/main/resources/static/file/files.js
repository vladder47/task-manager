angular.module('app').controller('filesController', function ($scope, $http, $routeParams) {
    const contextPath = 'http://localhost:8189/app';

    getFile = function (fileId) {
        $http.get(contextPath + '/api/v1/files' + fileId)
            .then(function (response) {
                $scope.File = response.data;
                console.log('FIE = ', $scope.File);
            });
    };

    getFile($routeParams.fileId);
});