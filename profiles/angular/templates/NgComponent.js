//= wrapped

angular
    .module("${moduleName}")
    .directive("${propertyName}", ${propertyName});

function ${propertyName}() {
    var directive = {
        restrict: "E",
        templateUrl: "/${templatePath}/${propertyName}.html",
        controller: ${controllerName},
        controllerAs: "vm",
        transclude: true,
        scope: {},
        bindToController: {
        }
    };

    return directive;

    /*@ngInject*/
    function ${controllerName}() {

    }
}
