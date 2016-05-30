// config

var app =  
angular.module('app')
  .config(
    [        '$controllerProvider', '$compileProvider', '$filterProvider', '$provide',
    function ($controllerProvider,   $compileProvider,   $filterProvider,   $provide) {
        
        // lazy controller, directive and service
        app.controller = $controllerProvider.register;
        app.directive  = $compileProvider.directive;
        app.filter     = $filterProvider.register;
        app.factory    = $provide.factory;
        app.service    = $provide.service;
        app.constant   = $provide.constant;
        app.value      = $provide.value;
    }
  ])
  .config(['$translateProvider', function($translateProvider){
    // Register a loader for the static files
    // So, the module will search missing translation tables under the specified urls.
    // Those urls are [prefix][langKey][suffix].
    $translateProvider.useStaticFilesLoader({
      prefix: 'i18n/',
      suffix: '.js'
    });
    // Tell the module what language to use by default
    $translateProvider.preferredLanguage('en');
    // Tell the module to store the language in the local storage
    $translateProvider.useLocalStorage();
  }])

 .config(['SocketClientProvider',function(SocketClientProvider){

}]);

angular.module('app').factory('httpRequestInterceptor',['$rootScope', '$q','$localStorage',
    function ($rootScope,$q,$localStorage) {
    return {
        request: function (config) {

            config.headers["X-Requested-With"] = 'XMLHttpRequest';
            if(config.url && config.url.indexOf("/oauth/token")!= -1){

                config.headers['Authorization'] = 'Basic '+ Base64.encode('clientapp:123456');
            }else if($localStorage.token){
                config.headers['Authorization'] = 'Bearer '+ $localStorage.token.access_token;
            }


            return config;
        },
        responseError: function(rejection) {
            // do something on error

            if(rejection.status === 401 ){
                console.log('Unauthorized access 401');
                $localStorage.token = undefined;
                $rootScope.$broadcast('unauthorized','You are not authorized');


            }else if(rejection.status === 403 ){
                console.log('Unauthorized access 403');
                $rootScope.$broadcast('forbidden','You are not authorized');
            }
            return $q.reject(rejection);
        }
    };
}]);

angular.module('app').config(function ($httpProvider) {

    $httpProvider.interceptors.push('httpRequestInterceptor');
});

