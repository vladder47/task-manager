angular.module('app').controller('createProjectController', function ($scope, $http, $log) {
    const contextPath = 'http://localhost:8189/app';

    fillTable = function () {
        $http.get(contextPath + '/api/v1/projects/create')
            .then(function (response) {
                $scope.LeaderList = response.data;
            });
    };

    $scope.SubmitCreateNewProject = function (){
        $http.post(contextPath + '/api/v1/projects/create', $scope.newProject)
            .then(function (response){
            $scope.push(response.data);
            });
    }

    var expanded = false;

    function showCheckboxes() {
        var checkboxes = document.getElementById("checkboxes");
        if (!expanded) {
            checkboxes.style.display = "block";
            expanded = true;
        } else {
            checkboxes.style.display = "none";
            expanded = false;
        }
    }

    fillTable();
});