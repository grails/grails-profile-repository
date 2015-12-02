describe("${moduleName} module", function() {
    var $httpBackend;

    beforeEach(angular.mock.module("${moduleName}", function() {
    }));

    beforeEach(angular.mock.inject(function(_\$httpBackend_) {
        \$httpBackend = _\$httpBackend_;
    }));

    afterEach(function() {
        \$httpBackend.verifyNoOutstandingExpectation();
        \$httpBackend.verifyNoOutstandingRequest();
    });

    describe("${className} domain", function() {

        var ${className};

        beforeEach(angular.mock.inject(function(_${className}_) {
            ${className} = _${className}_;
        }));

        it("should be tested", function() {
            expect(true).toEqual(false);
        });

    });

});
