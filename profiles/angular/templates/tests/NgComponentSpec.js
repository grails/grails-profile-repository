describe("${moduleName} module", function() {
    var scope;

    beforeEach(angular.mock.module("${moduleName}", function() {
    }));

    beforeEach(angular.mock.inject(function(\$rootScope) {
        scope = \$rootScope.\$new();
    }));

    describe("${propertyName} directive", function() {
        var element;

        beforeEach(angular.mock.inject(function (\$compile) {
            element = angular.element('<${tagName}></${tagName}>');
            \$compile(element)(scope);
            scope.\$digest();
        }));

        it("should be tested", function() {
            expect(true).toEqual(false);
        });

    });

});