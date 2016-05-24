/**
 * Created by techfocus on 5/20/16.
 */
angular.module('app').factory('Todo',['$resource','ServiceConfig', function($resource,ServiceConfig){

    var serviceUrl = ServiceConfig.getUrl();

    return  $resource(serviceUrl+'/api/todo/:id', { id: '@id' }, {
        update: {
            method: 'PUT' // this method issues a PUT request
        }
    });
}]);