(function() {
    "use strict";

    angular
        .module("application")
        .directive("applicationData", applicationData);

    function applicationData() {
        var directive = {
            templateUrl: "/application/applicationData.html",
            controller: "IndexController",
            controllerAs: "idxCtrl",
            scope: true,
            bindToController: {
            }
        };

        return directive;
    }

})();