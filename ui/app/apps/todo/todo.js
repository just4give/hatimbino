app.factory('todoStorage', ['ngStore', function (ngStore) {
    return ngStore.model('todo');
}]);

app.controller('TodoCtrl', ['$scope', '$location', '$filter', 'todoStorage','$log', 'Todo',
    function($scope, $location, $filter, todoStorage,$log,Todo) {

        

        Todo.query(function(data){
        $log.debug("Todo:",data);
        $scope.todos = data;
        $scope.remainingCount = $filter('filter')(data, {completed: false}).length;
    })

    $scope.newTodo = '';


    $scope.location = $location;

    $scope.$watch('location.path()', function (path) {
        $scope.statusFilter = { '/app/todo/active': {completed: false}, '/app/todo/completed': {completed: true} }[path];
    });

    $scope.$watch('remainingCount == 0', function (val) {
        $scope.allChecked = val;
    });

    $scope.addTodo = function () {
        var newTodo = $scope.newTodo.trim();
        if (newTodo.length === 0) {
            return;
        }

        /*var item = {
            id: todoStorage.nextId(),
            title: newTodo,
            completed: false
        };
        todos.push( todoStorage.create(item) );

        $scope.newTodo = '';
        $scope.remainingCount++;*/

        //new code added by appstacksolutions.com
        var todo = new Todo();

        todo.title = newTodo;
        todo.completed=false;
        todo.$save(function(data){
            $scope.todos.push(data);
            $scope.newTodo = '';
            $scope.remainingCount++;
        })

    };

    $scope.editTodo = function (todo) {
        todo.editedTodo = true;
        // Clone the original todo to restore it on demand.
        $scope.originalTodo = angular.extend({}, todo);
    };

    $scope.doneEditing = function (todo) {
        todo.editedTodo = false;
        todo.title = todo.title.trim();

        if (!todo.title) {
            $scope.removeTodo(todo);
        }

        //todoStorage.update(todo);
        //new code added by appstacksolutions.com
        todo.$update(function(data){
            $log.debug("Todo updated");
        })
    };

    $scope.revertEditing = function (todo) {
        todos[todos.indexOf(todo)] = $scope.originalTodo;
        $scope.doneEditing($scope.originalTodo);
    };

    $scope.removeTodo = function (todo) {
       /* $scope.remainingCount -= todo.completed ? 0 : 1;
        todos.splice(todos.indexOf(todo), 1);
        todoStorage.destroy(todo);*/

        //new code added by appstacksolutions.com
        todo.$delete(function(){
            $scope.remainingCount -= todo.completed ? 0 : 1;
            $scope.todos.splice($scope.todos.indexOf(todo), 1);
        })

    };

    $scope.todoCompleted = function (todo) {
       /* $scope.remainingCount += todo.completed ? -1 : 1;
        todoStorage.update(todo);*/

        //new code added by appstacksolutions.com
        todo.$update(function(data){
            $scope.remainingCount += todo.completed ? -1 : 1;
        })
    };

    $scope.clearCompletedTodos = function () {
        $scope.todos.filter(function (todo) {
            if(todo.completed){
                /*todos.splice(todos.indexOf(todo), 1);
                todoStorage.destroy(todo);*/
                //new code added by appstacksolutions.com
                todo.$delete(function(){

                    $scope.todos.splice($scope.todos.indexOf(todo), 1);
                })
            }
        });
    };

    $scope.markAll = function (completed) {
        $scope.todos.forEach(function (todo) {
            todo.completed = completed;
            todo.$update(function(){
                $scope.remainingCount--;
            })

        });
        //$scope.remainingCount = !completed ? todos.length : 0;
    };
}]);

