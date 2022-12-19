## Overview
This is a sample SpringBoot application using JDBC template. 
It also demonstrates how to deploy this as a micro service in Kubernetes along with a stateful MySQL Database.


## Kubernetes deployment

\# [Setup Kubenetes on local Mac using Microk8s](https://ubuntu.com/tutorials/install-microk8s-on-mac-os)
* brew install ubuntu/microk8s/microk8s
* `microk8s install`
* `microk8s start`
* `microk8s status --wait-ready`
* `microk8s kubectl get nodes`
* `microk8s kubectl get services`
* `microk8s enable storage` # enable storage
* `microk8s enable dns && microk8s stop && microk8s start`  # enable coredns


\# [Setup argocdFollow instructions at ArgoCD Getting Started guide](https://argo-cd.readthedocs.io/en/stable/getting_started/)
* Use `microk8s kubectl` wherever `kubectl` is mentioned when using microk8s
* `brew install argocd`
* `kubectl create namespace argocd`
* `kubectl apply -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml`
* `kubectl port-forward svc/argocd-server -n argocd 8080:443` # port-forward for argocd-server
* `kubectl -n argocd get secret argocd-initial-admin-secret -o jsonpath="{.data.password}" | base64 -d; echo` # get initial admin password
* `argocd login localhost:8080`
* `argocd account update-password` # change admin password
* `argocd cluster add microk8s` # register microk8s cluster with argocd
* `argocd context`  # list all available contexts
* `argocd app create spring-jdbc --repo https://github.com/shitalm/spring.git --path spring-jdbc/k8s --dest-server https://kubernetes.default.svc --dest-namespace default`  # register a new app argocd
* `argocd app sync`  # sync the app  
*  \# syncing the app  will work only after you have pushed container image in appropriate container registry i.e. either to dockerhub or to local microk8s container registry. See below

## Build the spring-jdbc app, deploy it to k8s and run

\# Docker daemon should be running on build server for following build options to work 

\# Select one of the following options to build and deploy MySQL and spring-jdbc service

\# You can either deploy local build to locally running microk8s or you can push the container image to docker hub

`bash microk8s-push.sh` # # build and push docker image to local microk8s container registry

`bash docker-push.sh` # build and push docker image to docker hub

`kubectl port-forward svc/mysql 33306:3306` # port forward mysql, if you are using microk8s, kubectl should be used as `microk8s kubectl`

`mysql -h 127.0.0.1 -u root -p -P 33306 < mysql-setup.txt` # setup mysql - create database, user and table

`kubectl port-forward svc/spring-jdbc 8080:8080` # port forward spring-jdbc service

`curl -v localhost:8080/hello`  # should return Hello World!

`kubectl get pods`  # find pod name for spring-jdbc container

`kubectl get logs -f <spring-jdbc-pod-name`  # check spring-jdbc service logs


## Notes
* To specify a different location for application properties, specify it in commandline as follows:
  
  <code>java --spring.config.location=file:///tmp/config/application.properties`</code>
