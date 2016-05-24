   

#Run Service
1. CWD to service folder
2. Change src/main/resources/application.properties according to your environment
<pre>
spring.application.name=service
server.port=8500
spring.data.mongodb.uri=mongodb://localhost:27017/todos
</pre>
2. Issue <pre>mvn clean build</pre>
3. Issue <pre>java -jar target/todo-service-0.0.1-SNAPSHOT.jar</pre>


#Run UI

1. CWD to ui folder
2. Install Node.js and NPM on your computer (if not yet done)
3. Issue <pre>npm install</pre>
4. Issue <pre>npm start</pre>
5. Open "http://localhost/app/" in browser

<h6>Note: Modify ui/app/scripts/config.service.js pointing to your backend service</h6>
<pre>
//TODO: change this url based on your environment
    var serviceUrl = "http://localhost:8500";
</pre> 