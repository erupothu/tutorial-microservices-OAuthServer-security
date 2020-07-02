# tutorial-microservices-OAuthServer

<b>OAurh Server</b><br> port-8080
Role based auth, permission based uri access<br>
Both in-memory and databased based authuntication code available <br>
spring-cloud-starter-security (for security) <br>
spring-cloud-starter-oauth2 (for authenticating with the Oauth Server) <br>

<b>Eurek Server(8761) APi Gateway(8083)</b> <br>
for registry and discovery and routing

<b>student-service(8071) and book service(8081)<b> <br>
spring-cloud-starter-security (for security) <br>
spring-cloud-starter-oauth2 (for authenticating with the Oauth Server) <br>


first rung AuthServer, Eureka server and Api Gateway server<br>
then Student-service, book-service <br>

now
localhost:8083/api/book/welcome (fetch the data from book service by authenticating with Auth Server) <br>
localhost:8083/api/student/welcome (fetch data from student service only if authenticated with auth server)  <br>

Every service Authenicated with stored token till the session timeout happpens. else authenticate again <br>

