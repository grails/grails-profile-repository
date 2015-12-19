//= wrapped

angular
    .module("@grails.codegen.defaultPackage@.core")
    .factory("DomainServiceFactory", DomainServiceFactory);

function DomainServiceFactory($resource) {
    return function(url) {
        return $resource(
            url + "/:id",
            null,
            {"update": {method: "PUT"}, "list": {method: "GET", isArray: true}}
        );
    }
}

