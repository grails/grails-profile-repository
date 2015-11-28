//= require angular/angular
//= require_self

(function() {
    "use strict";

    angular.module("siteConfig", [])
        .constant("contextPath", window.contextPath)
        .config(config);

    function config($httpProvider) {
        $httpProvider.defaults.headers.common["X-Requested-With"] = "XMLHttpRequest";
        $httpProvider.interceptors.push(httpRequestInterceptor);
    }

    function httpRequestInterceptor(contextPath) {
        return {
            request: function (config) {
                if (!config.url.indexOf("/") == 0 && contextPath) {
                    config.url = contextPath + "/" + config.url;
                }
                return config;
            }
        };
    }
})();