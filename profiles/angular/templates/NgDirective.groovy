(function() {
    "use strict";

    angular
        .module("${moduleName}")
        .directive("${propertyName}", ${propertyName});

    function ${propertyName}() {
        var directive = {
            link: link,
            templateUrl: "/${templatePath}/templates/<example_template>.html",
            controller: "<ExampleController>",
            controllerAs: "<exampleCtrl>",
            scope: true,
            bindToController: {
            }
        };

        return directive;

        function link(scope, element, attrs) {

        }
    }

})();