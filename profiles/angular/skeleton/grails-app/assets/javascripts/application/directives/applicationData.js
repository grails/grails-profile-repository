//= wrapped

angular
    .module("application")
    .directive("applicationData", applicationData);

function applicationData() {
    var directive = {
        restrict: "E",
        templateUrl: "/application/applicationData.html",
        controller: "IndexController",
        controllerAs: "vm",
        transclude: true,
        scope: {},
        bindToController: {
        }
    };

    return directive;
}
