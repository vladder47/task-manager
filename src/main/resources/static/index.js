(function () {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'about/about.html',
                controller: 'aboutController'
            })
            .when('/projects', {
                templateUrl: 'project/projects.html',
                controller: 'projectsController'
            })
            .when('/projects/create', {
                templateUrl: 'project/create-project.html',
                controller: 'createProjectController'
            })
            .when('/auth', {
                templateUrl: 'auth/auth.html',
                controller: 'authController'
            })
            .when('/registration', {
                templateUrl: 'registration/registration.html',
                controller: 'registrationController'
            })
            .when('/projects/edit/:projectId', {
                templateUrl: 'project/edit_project.html',
                controller: 'editProjectController'
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
            .when('/tasks/edit/:taskId', {
                templateUrl: 'task/edit_task.html',
                controller: 'editTaskController'
            })
            .when('/all', {
                templateUrl: 'task/users_tasks.html',
                controller: 'usersTasksController'
            });

        // $httpProvider.interceptors.push(function ($q, $location) {
        //     return {
        //         'responseError': function (rejection, $localStorage, $http) {
        //             var defer = $q.defer();
        //             if (rejection.status == 401 || rejection.status == 403) {
        //                 console.log('error: 401-403');
        //                 $location.path('/auth');
        //                 if (!(localStorage.getItem("localUser") === null)) {
        //                     delete $localStorage.currentUser;
        //                     $http.defaults.headers.common.Authorization = '';
        //                     console.log('zxc');
        //                 }
        //                 console.log(rejection.data);
        //                 var answer = JSON.parse(rejection.data);
        //                 console.log(answer);
        //                 // window.alert(answer.message);
        //             }
        //             defer.reject(rejection);
        //             return defer.promise;
        //         }
        //     };
        // });
    }
    function run($rootScope, $http, $localStorage) {
        if ($localStorage.currentUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.currentUser.token;
        }
    }
})();