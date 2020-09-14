angular.module('app').controller('taskPageController', function ($scope, $http, $routeParams, $localStorage) {
    const contextPath = 'http://localhost:8189/app';
    $scope.newComment = {};
    $scope.newComment.task = {};
    $scope.newComment.user = {};
    $scope.notification = {};
    $scope.notification.users = [];
    $scope.notification.text = {};

    getTask = function (taskId) {
        $http.get(contextPath + '/api/v1/tasks/' + taskId)
            .then(function (response) {
                $scope.task = response.data;
            });
        $http.get(contextPath + '/api/v1/users/dtos/task/' + taskId)
            .then(function (response) {
                $scope.senders = response.data;
                let users = '';
                for (let key in response.data) {
                    users += response.data[key]['username'] + '; ';
                }
                $scope.users = users;
            });
        $http.get(contextPath + '/api/v1/comments/' + taskId)
            .then(function (response) {
                $scope.comments = response.data;
                $scope.currentUser = $localStorage.currentUser;
            });
        $http.get(contextPath + '/api/v1/users/current')
            .then(function (response) {
                $scope.newComment.user.id = response.data.id;
            });
    };

    $scope.sendComment = function() {
        $scope.newComment.task.id = $routeParams.taskId;
        $scope.newComment.parent = 0;
        $http.post(contextPath + '/api/v1/comments', $scope.newComment)
            .then(function () {
                if (parseComment($scope.newComment.text) !== null) {
                    $http.get(contextPath + '/api/v1/users/dtos/' + parseComment($scope.newComment.text))
                        .then(function (response) {
                            $scope.notification.users.push({'id': response.data.id});
                            $scope.notification.text = "Вам оставил комментарий пользователь " + $localStorage.currentUser.username;
                            $http.post(contextPath + '/api/v1/notifications', $scope.notification)
                                .then(function() {

                                });
                        });
                }
                getTask($routeParams.taskId);
                $scope.newComment.text = null;
            });
    };

    parseComment = function (text){
        if (text[0] !== '@'){
            return null;
        }
        var i = text.indexOf(' ');
        var name = text.substr(1, i);
        return name;
    }

    getTask($routeParams.taskId);

    getFiles = function (taskId){
        console.log(taskId);
        $http.get(contextPath + '/api/v1/tasks/' + taskId + '/files')
            .then(function (response) {
                $scope.FileList = response.data;
                console.log('LOG = ', $scope.FileList);
            });
    }

    getFiles($routeParams.taskId);

    var formdata = new FormData();
    $scope.getTheFiles = function ($files) {
        angular.forEach($files, function (value, key) {
            formdata.append(key, value);
        });
    };

    $scope.uploadFiles = function (taskId){
        console.log('ADD FILE');

        var file = $scope.myFile;
        console.log('file is ' );
        console.dir(file);
        var uploadUrl = contextPath + '/api/v1/tasks/' + taskId + '/files';
        fileUpload.uploadFileToUrl(file, uploadUrl);

        var request = {
            method: 'POST',
            url: uploadUrl,
            data: formdata,
            headers: {
                'Content-Type': undefined
            }
        };

        // SEND THE FILES.
        $http(request)
            .success(function (d) {
                alert(d);
            })
            .error(function () {
            });
    };

});