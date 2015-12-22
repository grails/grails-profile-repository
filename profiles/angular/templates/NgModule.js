//= wrapped ${dependencies.collect{ '\n//= require ' + it.value }.join('\n') }
//= require_self
//= require_tree services
//= require_tree controllers
//= require_tree directives
//= require_tree domain
//= require_tree templates

angular.module("${fullName}", [${dependencies.collect{it.key}.join(', ')}]);

