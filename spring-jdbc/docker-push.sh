# build and push docker image to docker hub
# this assumes that deployment.yaml refers docker hub image
mvn package
docker build -t spring-jdbc:1.0 .
docker tag spring-jdbc:1.0 shitalm/spring-jdbc:1.0
docker push shitalm/spring-jdbc:1.0
# sync may not trigger any reloading of Pod if deployment.yaml or image tag version hasn't changed
argocd app sync spring-jdbc