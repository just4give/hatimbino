angular.module('app').controller('SignInCtrl', ['$scope','$rootScope','AuthService','$state',function($scope,$rootScope,AuthService,$state){

    $scope.user={};

    $scope.signin = function(){
        AuthService.authenticate($scope.user.email, $scope.user.password)
            .then(function(data){
                if(data.success){
                    $rootScope.user = data.user;
                    $state.go('app.dashboard');
                }
            },function(err){

            });
    }

}]);
