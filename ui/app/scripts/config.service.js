angular.module('app').factory('ServiceConfig',['$resource', function($resource){

	//TODO: change this url based on your environment
    var serviceUrl = "http://localhost:8500";

    return {
        getUrl : function(){
            return serviceUrl;
        }
    }

}]);