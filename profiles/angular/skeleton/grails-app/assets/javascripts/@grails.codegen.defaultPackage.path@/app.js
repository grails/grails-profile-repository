//= wrapped
//= require /@grails.codegen.defaultPackage.path@/core/core
//= require /@grails.codegen.defaultPackage.path@/index/index
//= require_self
//= require_tree services
//= require_tree controllers
//= require_tree directives
//= require_tree domain
//= require_tree templates

angular.module("@grails.codegen.defaultPackage@", [
    "@grails.codegen.defaultPackage@.core",
    "@grails.codegen.defaultPackage@.index"
]);
