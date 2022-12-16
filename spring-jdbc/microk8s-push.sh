## build and push docker image to local microk8s container registry
# this assumes that deployment.yaml points to local image
mvn clean package
docker build . -t spring-jdbc:local
docker save spring-jdbc > target/spring-jdbc.tar
multipass transfer target/spring-jdbc.tar microk8s-vm:/tmp/spring-jdbc.tar
microk8s ctr image import /tmp/spring-jdbc.tar
argocd app sync spring-jdbc --local k8s/