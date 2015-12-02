//= wrapped

angular
    .module("${moduleName}")
    .directive("${propertyName}", ${propertyName});

function ${propertyName}() {
    var directive = {
        link: link,
        template: "",
        scope: {}
    };

    return directive;

    function link(scope, element, attrs) {

    }
}