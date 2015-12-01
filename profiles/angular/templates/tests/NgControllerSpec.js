describe("${moduleName} module", function() {

    beforeEach(angular.mock.module("${moduleName}", function() {

    }));

    describe("${name}", function() {

        var scope, ctrl;

        beforeEach(angular.mock.inject(function(\$controller, \$rootScope) {
            scope = \$rootScope.\$new();
            ctrl = \$controller("${name}", {\$scope: scope});
        }));

        it("should be tested", function() {
            expect(true).toEqual(false);
        });
    });
});
