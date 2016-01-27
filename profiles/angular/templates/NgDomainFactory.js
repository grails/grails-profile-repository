//= wrapped

angular
    .module("${moduleName}")
    .factory("${className}", ${className});

function ${className}(domainServiceFactory) {
    return domainServiceFactory("${propertyName}/:id");
}
