/**
 * Created by appstacksolutions.com on 5/24/2016.
 */
angular.module('app').provider('SocketClient',[function(){

    this.url ="http://localhost:8500/todo";
    this.connected = false;
    this.stompClient;


    this.$get = ['$mdToast',function($mdToast){
        var that = this;

        return {
            subscribe :function(){
                var that = this;
                var socket = new SockJS(that.url);
                stompClient = Stomp.over(socket);
                stompClient.connect({}, function(frame) {

                    $mdToast.show(
                        $mdToast.simple()
                            .content('Connected....')
                            .hideDelay(3000)
                    );
                    connected = true;
                    stompClient.subscribe('/topic/todo', function(response){
                        $mdToast.show(
                            $mdToast.simple()
                                .content(JSON.parse(response.body).content)
                                .hideDelay(3000)
                        );
                    });

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
