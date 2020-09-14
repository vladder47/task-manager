// import { Component, ElementRef, ViewChild } from '@angular/core';
// import { FileUploader } from 'ng2-file-upload';

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

// let AppComponent = class AppComponent {
//     ngOnInit() {
//         const headers = [{ name: 'Accept', value: 'application/json' }];
//         this.uploader = new FileUploader({ url: 'api/files', autoUpload: true, headers: headers });
//         this.uploader.onCompleteAll = () => alert('File uploaded');
//     }
//     fileOverAnother(e) {
//         this.isDropOver = e;
//     }
//     fileClicked() {
//         this.fileInput.nativeElement.click();
//     }
// };
// __decorate([
//     ViewChild('fileInput'),
//     __metadata("design:type", ElementRef)
// ], AppComponent.prototype, "fileInput", void 0);
// AppComponent = __decorate([
//     Component({
//         selector: 'app-root',
//         templateUrl: './edit-tasks.html',
//         styleUrls: ['./app.component.scss']
//     })
// ], AppComponent);
// export { AppComponent };