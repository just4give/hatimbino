angular.module('app').factory('ServiceConfig',['$resource', function($resource){

    var serviceUrl = "http://localhost:8500";

    return {
        getUrl : function(){
            return serviceUrl;
        }
    }

}]);