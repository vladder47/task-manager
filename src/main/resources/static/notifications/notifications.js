angular.module('app').controller('notificationsController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app';

    fillTable = function () {
        $http.get(contextPath + '/api/v1/notifications/show')
            .then(function (response) {
                $scope.NotificationList = response.data;
            });
    };

    fillTable();
});

