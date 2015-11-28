describe("${moduleName} module", function() {

    beforeEach(angular.mock.module("${moduleName}", function() {

    }));

    describe("${propertyName}", function() {

        var scope, ctrl;

        beforeEach(angular.mock.inject(function(\$controller, \$rootScope) {
            scope = \$rootScope.\$new();
            ctrl = \$controller("${controllerName}", {\$scope: scope});
        }));

        it("should be tested", function() {
            expect(true).toEqual(false);
        });
    });
});
