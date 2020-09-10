angular.module('app').controller('projectsController', function ($scope, $http, $routeParams) {
    const contextPath = 'http://localhost:8189/app';

    fillTable = function (pageNumber) {
        if (pageNumber === undefined) {
            pageNumber = 1;
        }
        $http.get(contextPath + '/api/v1/projects?page=' + pageNumber)
            .then(function (response) {
                $scope.ProjectsList = response.data.content;
                $scope.pages = getPager(response.data.totalPages, response.data.pageable.pageNumber);
                $scope.page = response.data;
            });
    };

    $scope.findProjects = function (pageNumber) {
        fillTable(pageNumber);
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

    fillTable($routeParams.page);
});