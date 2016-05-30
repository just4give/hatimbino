angular.module('app').factory('ServiceConfig',[ function(){

	//TODO: change this url based on your environment
    var serviceUrl = "http://localhost:8500";

    return {
        getUrl : function(){
            return serviceUrl;
        }
    }

}]);