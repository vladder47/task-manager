angular.module('app').controller('usersTasksController', function ($scope, $http, $routeParams) {
    const contextPath = 'http://localhost:8189/app';
    $scope.form = {};
    $scope.form.sort = {};

    fillTable = function (pageNumber, params) {
        if (pageNumber === undefined) {
            pageNumber = 1;
        }
        $http.get(contextPath + '/api/v1/tasks/all?page=' + pageNumber + getUrl(params))
            .then(function (response) {
                $scope.TasksList = response.data.content;
                $scope.pages = getPager(response.data.totalPages, response.data.pageable.pageNumber);
                $scope.page = response.data;
            });
        $http.get(contextPath + '/api/v1/tasks/status')
            .then(function (response) {
                $scope.statuses = response.data;
            });
        $http.get(contextPath + '/api/v1/tasks/priority')
            .then(function (response) {
                $scope.priorities = response.data;
            });
    };

    $scope.applyFilter = function (pageNumber) {
        fillTable(pageNumber, $scope.form);
    };

    function getUrl(params) {
        var result = "";
        if (params['title']) {
            result += '&title=' + params['title'];
        }if (params['leader']) {
            result  += '&leader=' + params['leader'];
        }if (params['project']) {
            result  += '&project=' +  params['project'];
        }if (params['user']) {
            result  += '&user=' +  params['user'];
        }
        if (params['status']) {
            for (let key in params['status']) {
                if (params['status'][key] === true) {
                    result += `&status=${key}`;
                }
            }
        }
        if (params['priority']) {
            for (let key in params['priority']) {
                if (params['priority'][key] === true) {
                    result += `&priority=${key}`;
                }
            }
        }
        if (params['sort']) {
            for (let key in params['sort']) {
                if (params['sort'][key] !== 'none') {
                    result += `&sort=${params['sort'][key]}`;
                }
            }
        }

        return result;
    }

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

    fillTable($routeParams.page, $scope.form);
});