//= wrapped

angular
    .module("@grails.codegen.defaultPackage@.index")
    .controller("IndexController", IndexController);

function IndexController(applicationDataFactory, contextPath, $state) {
    var vm = this;

    vm.contextPath = contextPath;

    applicationDataFactory.get().then(function(response) {
        vm.applicationData = response.data;
    });

    vm.stateExists = function(name) {
        return $state.get(name) != null;
    };

}
