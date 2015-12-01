describe("${moduleName} module", function() {

    beforeEach(angular.mock.module("${moduleName}", function() {

    }));

    describe("${propertyName} directive", function() {

        var element, scope;

        beforeEach(angular.mock.inject(function (\$compile, \$rootScope) {
            scope = \$rootScope.\$new();
            element = angular.element('<${tagName}></${tagName}>');
            \$compile(element)(scope);
            scope.\$digest();
        }));

        it("should be tested", function() {
            expect(true).toEqual(false);
        });

    });

});