(function() {
    "use strict";

    angular
        .module("${moduleName}")
        .factory("${className}", ${className});

    function ${className}(\$resource) {
        return \$resource(
                    "${propertyName}/:id",
                    null,
                    {"update": {method: "PUT"}, "list": {method: "GET", isArray: true}}
               );
    }

})();
