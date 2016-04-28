//= wrapped

angular
    .module("${moduleName}")
    .factory("${className}", ${className});

function ${className}(\$resource) {
    var ${className} = \$resource(
        "${propertyName}/:id",
        {"id": "@id"},
        {"update": {method: "PUT"}, "list": {method: "GET", isArray: true}}
    );
    return ${className};
}
