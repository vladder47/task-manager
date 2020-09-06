angular.module('app').controller('tasksController', function ($scope, $http, $routeParams) {
    const contextPath = 'http://localhost:8189/app';

    fillTable = function (pageNumber, projectId) {
        if (pageNumber === undefined) {
            pageNumber = 1;
        }
        $http.get(contextPath + '/api/v1/tasks?page=' + pageNumber + '&project=' + projectId)
            .then(function (response) {
                $scope.TasksList = response.data.content;
                $scope.pages = getPager(response.data.totalPages, response.data.pageable.pageNumber);
                $scope.page = response.data;
                $scope.projectId = projectId;
            });
    };

    getTask = function (taskId) {
        $http.get(contextPath + '/api/v1/tasks/' + taskId)
            .then(function (response) {
                $scope.task = response.data.content;
            });
    };

    $scope.findTasks = function (pageNumber) {
        fillTable(pageNumber, $routeParams.project);
    };

    // google's pagination logic
    function getPager(totalPages, currentPage) {
        currentPage = currentPage || 1;

        let startPage, endPage;
        if (totalPages <= 10) {
            startPage = 1;
            endPage = totalPages;
        } else {
            if (currentPage <= 6) {
                startPage = 1;
                endPage = 10;
            } else if (currentPage + 4 >= totalPages) {
                startPage = totalPages - 9;
                endPage = totalPages;
            } else {
                startPage = currentPage - 5;
                endPage = currentPage + 4;
            }
        }

        let pages = [];
        for (let i = startPage; i < endPage + 1; i++) {
            pages.push(i);
        }

        return pages;
    }

    fillTable($routeParams.page, $routeParams.project);
});