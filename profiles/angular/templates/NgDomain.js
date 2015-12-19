//= wrapped

angular
    .module("${moduleName}")
    .factory("${className}", ${className});

function ${className}(DomainServiceFactory) {
    return DomainServiceFactory("${propertyName}");
}
