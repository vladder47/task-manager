(function () {
    'use strict';

    angular
        .module('app', ['ngRoute'])
        .config(config)
        .run();

    function config($routeProvider) {
        $routeProvider
            .when('/projects', {
                templateUrl: 'project/projects.html',
                controller: 'projectsController'
            })
            .when('/projects/create', {
                templateUrl: 'create_project/create-project.html',
                controller: 'createProjectController'
            });
    }
})();