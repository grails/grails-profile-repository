//= wrapped

angular
    .module("@grails.codegen.defaultPackage@.index")
    .directive("applicationData", applicationData);

function applicationData() {
    var directive = {
        restrict: "E",
        templateUrl: "/@grails.codegen.defaultPackage.path@/index/applicationData.html",
        controller: "IndexController",
        controllerAs: "vm",
        transclude: true,
        scope: {},
        bindToController: {
        }
    };

    return directive;
}
