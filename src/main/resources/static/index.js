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
            })
            .when('/tasks', {
                templateUrl: 'task/tasks.html',
                controller: 'tasksController'
            })
            .when('/tasks/create/:projectId', {
                templateUrl: 'task/create_task.html',
                controller: 'createTaskController'
            })
            .when('/tasks/:taskId', {
                templateUrl: 'task/task_page.html',
                controller: 'taskPageController'
            })
    }
})();