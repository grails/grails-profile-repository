//= require /angular/angular
//= require /angular/angular-resource
//= require /siteConfig
//= require_self
//= require_tree services
//= require_tree controllers
//= require_tree directives
//= require_tree domain
//= require_tree templates

(function() {
    "use strict";

    angular.module("${fullName}", [${dependencies.join(', ')}]);

})();