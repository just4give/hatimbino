/**
 * Created by appstacksolutions.com on 5/24/2016.
 */
angular.module('app').provider('SocketClient',[function(){

    this.url ="http://localhost:8500/socket";
    this.connected = false;
    this.stompClient;


    this.$get = ['$mdToast','$http','$rootScope','$localStorage',function($mdToast,$http,$rootScope,$localStorage){
        var that = this;

        return {
            subscribe :function(topic){
                var that = this;

                var socket = new SockJS(that.url);
                stompClient = Stomp.over(socket);
                var headers = {};
                headers['Authorization'] = 'Bearer '+ $localStorage.token.access_token;
                headers["X-Requested-With"] = 'XMLHttpRequest';

                stompClient.connect(headers, function(frame) {
                     if( !$rootScope.connected ){
                        $rootScope.connected = true;
                        $mdToast.show(
                            $mdToast.simple()
                                .content('Connected Web Socket ....')
                                .hideDelay(3000)
                        );
                        stompClient.subscribe('/topic/'+topic, function(response){
                            $mdToast.show(
                                $mdToast.simple()
                                    .content(JSON.parse(response.body).content)
                                    .hideDelay(3000)
                            );
                        });

                    }

                });
            },
            send: function(topic, message){
                if(stompClient){
                    stompClient.send(topic, {}, JSON.stringify({ 'content': message }));
                }

            },
            url : that.url
        }
    }]


}]);
