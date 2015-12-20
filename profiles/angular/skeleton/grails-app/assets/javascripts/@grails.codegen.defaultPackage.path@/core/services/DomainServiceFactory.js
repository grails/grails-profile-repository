//= wrapped

/*
    NOTE: This file is used by the create-ng-domain action.
    You can modify or extend the DomainServiceFactory but it is recommended that you not delete it.
*/

angular
    .module("@grails.codegen.defaultPackage@.core")
    .factory("DomainServiceFactory", DomainServiceFactory);

function DomainServiceFactory($resource) {
    return function(url, paramDefaults, actions, options) {
        return $resource(
            url,
            paramDefaults || null,
            actions || {"update": {method: "PUT"}, "list": {method: "GET", isArray: true}},
            options || {}
        );
    }
}

