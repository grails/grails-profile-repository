describe("${moduleName} module", function() {

    beforeEach(angular.mock.module("${moduleName}", function() {

    }));

    describe("${className} domain", function() {

        var ${className}, \$httpBackend;

        beforeEach(angular.mock.inject(function(_${className}_, _\$httpBackend_) {
            \$httpBackend = _\$httpBackend_;
            ${className} = _${className}_;
        }));

        afterEach(function() {
            \$httpBackend.verifyNoOutstandingExpectation();
            \$httpBackend.verifyNoOutstandingRequest();
        });

        it("should be tested", function() {
            expect(true).toEqual(false);
        });
    });
});
