//= wrapped

angular
    .module("@grails.codegen.defaultPackage@.home")
    .directive("applicationData", applicationData);

function applicationData() {
    var directive = {
        restrict: "E",
        templateUrl: "/@grails.codegen.defaultPackage.path@/home/applicationData.html",
        controller: "IndexController",
        controllerAs: "vm",
        transclude: true,
        scope: {},
        bindToController: {
        }
    };

    return directive;
}
