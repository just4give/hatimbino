angular.module('app').factory('AuthService',['$localStorage','$http','$q','ServiceConfig','SocketClient',
    function($localStorage,$http,$q,ServiceConfig,SocketClient){

    var authenticate = function(username, password){
        var deferred = $q.defer();

        $http.post(ServiceConfig.getUrl()+'/oauth/token?grant_type=password&scope=read%20write&client_secret=123456&client_id=clientapp'+
        '&username='+username+'&password='+password)
            .success(function(token){
                $localStorage.token = token;


                $http.get(ServiceConfig.getUrl()+'/api/info/user')
                    .success(function(user){
                        if(user.roles.indexOf("ADMIN")!=-1){
                            SocketClient.subscribe(user.username);
                        }
                        deferred.resolve({success:true, user: user});


                    })
                    .error(function(err){
                        deferred.reject(err);
                    })

            }).error(function(err){
                deferred.reject(err);
            });
        return deferred.promise;
    }
    var refreshToken = function(refresh_token){
        var deferred = $q.defer();

        $http.post(ServiceConfig.getUrl()+'/oauth/token?grant_type=refresh_token&client_secret=123456&client_id=clientapp'+
                '&refresh_token='+refresh_token)
            .success(function(token){
                $localStorage.token = token;
                deferred.resolve(token);

            }).error(function(err){
            deferred.reject(err);
        });
        return deferred.promise;
    }
    var reloadLoggedinUser = function(){
        var deferred = $q.defer();

        $http.get(ServiceConfig.getUrl()+'/api/info/user')
            .success(function(user){
                if(user.roles.indexOf("ADMIN")!=-1){
                    SocketClient.subscribe(user.username);
                }
                deferred.resolve({success:true, user: user});


            })
            .error(function(err){
                deferred.reject(err);
            })

        return deferred.promise;
        }

    var isAuthorized = function(){
        var deferred = $q.defer();


        if($localStorage.token && $localStorage.token.access_token){

            deferred.resolve(true);
        }else{
            console.log('unauthorized');
            deferred.reject('not authorized');
        }

        return deferred.promise;
    }

    return{
        authenticate :authenticate,
        isAuthorized:isAuthorized,
        refreshToken:refreshToken,
        reloadLoggedinUser:reloadLoggedinUser
    }

}]);