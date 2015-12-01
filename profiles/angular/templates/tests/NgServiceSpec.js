describe("${moduleName} module", function() {

    beforeEach(angular.mock.module("${moduleName}", function() {

    }));

    describe("${name}", function() {

        var ${name};

        beforeEach(angular.mock.inject(function(_${name}_) {
            ${name} = _${name}_;
        }));

        it("should be tested", function() {
            expect(true).toEqual(false);
        });
    });
});
