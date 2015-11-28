describe('Application module', function() {

    beforeEach(angular.mock.module('application', function() {
    }));

    describe("IndexController", function() {
        var controller, deferred, applicationDataFactory, scope;

        beforeEach(angular.mock.inject(function($q, $controller, $rootScope) {

            applicationDataFactory = {
                get: function() {
                    deferred = $q.defer();
                    return deferred.promise;
                }
            };

            spyOn(applicationDataFactory, 'get').and.callThrough();

            scope = $rootScope.$new();
            controller = $controller('IndexController', {$scope: scope, applicationDataFactory: applicationDataFactory, contextPath: "/GrailsApp"});
        }));

        beforeEach(function() {
            expect(applicationDataFactory.get.calls.count()).toEqual(1);
        });

        it("should assign the contextPath to a local variable on the vm", function() {
            expect(controller.contextPath).toEqual('/GrailsApp');
        });

        it("should call the applicationDataFactory and assign data on success", function() {
            deferred.resolve({data: "Hello"});
            scope.$digest();
            expect(controller.applicationData).toEqual("Hello");
        });

        it("should call the applicationDataFactory and do nothing on error", function() {
            deferred.reject();
            scope.$digest();
            expect(controller.applicationData).not.toBeDefined();
        });

    });

});
