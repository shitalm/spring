## To specify a different location for application properties, specify it in commandline as follows
<code>java --spring.config.location=file:///tmp/config/application.properties</code>

## setup mysql
mysql -h 127.0.0.1 -u root -p -P 33306 < mysql-setup.txt

## build and push docker image to local microk8s container registry
\# Docker daemon should be running on build server for this to work
bash microk8s-push.sh

## build and push docker image to docker hub
bash docker-push.sh


