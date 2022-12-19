\# Docker daemon should be running on build server for this to work 

\# Select one of the following options to build and deploy MySQL and spring-jdbc service

\# You can either deploy local build to locally running microk8s or you can push the container image to docker hub

`bash microk8s-push.sh` # # build and push docker image to local microk8s container registry

`bash docker-push.sh` # build and push docker image to docker hub

`kubectl port-forward svc/mysql 33306:3306` # port forward mysql, if you are using microk8s, kubectl should be microk8s kubectl

`mysql -h 127.0.0.1 -u root -p -P 33306 < mysql-setup.txt` # setup mysql - create database, user and table

`kubectl port-forward svc/spring-jdbc 8080:8080` # port forward spring-jdbc service


## Notes
* To specify a different location for application properties, specify it in commandline as follows:
  
  <code>java --spring.config.location=file:///tmp/config/application.properties`</code>
