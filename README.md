# Spring Data JPA / RIFF Function Demo

This is a basic demo showing how to use a riff function which is a Spring Boot application using 
Spring Data JPA. In this example we deploy the function to riff 0.5.0. The database used here is an 
embedded H2, but you could easily use an external database if required

## Steps

1. Ensure you have installed riff. Use the guide below to get started with riff 

https://projectriff.io/docs/v0.5/getting-started

``` bash
$ riff --version
riff version 0.5.0-snapshot (668e2c9ee2458f0f7a07761a5690ffac534c3914)
```

2. Ensure you have installed riff into a k8s cluster of your choice. I am using GKE cluster which you can 
also do by following this guide

https://projectriff.io/docs/v0.5/getting-started/gke

You will be ready once you run this command showing the status of the install

``` bash
$ riff doctor
NAMESPACE     STATUS
default       ok
riff-system   ok

RESOURCE                                  NAMESPACE     NAME       READ      WRITE
configmaps                                riff-system   builders   allowed   n/a
configmaps                                default       *          allowed   allowed
secrets                                   default       *          allowed   allowed
pods                                      default       *          allowed   n/a
pods/log                                  default       *          allowed   n/a
applications.build.projectriff.io         default       *          allowed   allowed
containers.build.projectriff.io           default       *          allowed   allowed
functions.build.projectriff.io            default       *          allowed   allowed
deployers.core.projectriff.io             default       *          allowed   allowed
processors.streaming.projectriff.io       default       *          missing   missing
streams.streaming.projectriff.io          default       *          missing   missing
kafkaproviders.streaming.projectriff.io   default       *          missing   missing
adapters.knative.projectriff.io           default       *          allowed   allowed
deployers.knative.projectriff.io          default       *          allowed   allowed
```

3. In this demo we will use this GitHub repo as the source for the function we are creating. That is done 
as follows

**riff function create person-service --handler findPersonById --git-repo https://github.com/papicella/SpringDataJPAFunction.git --tail**

``` bash
$ riff function create person-service --handler findPersonById --git-repo https://github.com/papicella/SpringDataJPAFunction.git --tail
Created function "person-service"
default/person-service-function-6c5dd-build-1-vd5zs-build-pod[prepare]: prepare:fetch.go:66: Successfully cloned "https://github.com/papicella/SpringDataJPAFunction.git" @ "4769b0d25830a4cb11ec4afa691376bea149839a" in path "/workspace"
default/person-service-function-6c5dd-build-1-vd5zs-build-pod[analyze]: Writing metadata for uncached layer 'org.cloudfoundry.openjdk:openjdk-jre'
default/person-service-function-6c5dd-build-1-vd5zs-build-pod[analyze]: Writing metadata for uncached layer 'io.projectriff.java:streaming-http-adapter'
default/person-service-function-6c5dd-build-1-vd5zs-build-pod[analyze]: Writing metadata for uncached layer 'io.projectriff.java:java-function'
default/person-service-function-6c5dd-build-1-vd5zs-build-pod[analyze]: Writing metadata for uncached layer 'io.projectriff.java:riff-invoker-java'
default/person-service-function-6c5dd-build-1-vd5zs-build-pod[build]:
default/person-service-function-6c5dd-build-1-vd5zs-build-pod[build]: Cloud Foundry OpenJDK Buildpack 1.0.64
default/person-service-function-6c5dd-build-1-vd5zs-build-pod[build]:   OpenJDK JDK 11.0.5: Contributing to layer
default/person-service-function-6c5dd-build-1-vd5zs-build-pod[build]:     Reusing cached download from buildpack
default/person-service-function-6c5dd-build-1-vd5zs-build-pod[build]:     Expanding to /layers/org.cloudfoundry.openjdk/openjdk-jdk

...

default/person-service-function-6c5dd-build-1-vd5zs-build-pod[build]: Java Function Buildpack 0.2.0-BUILD-SNAPSHOT
default/person-service-function-6c5dd-build-1-vd5zs-build-pod[build]:   Java findPersonById: Reusing cached layer
default/person-service-function-6c5dd-build-1-vd5zs-build-pod[build]:   riff Streaming HTTP Adapter 0.0.1: Reusing cached layer
default/person-service-function-6c5dd-build-1-vd5zs-build-pod[build]:   riff Java Invoker 0.2.0+snapshot: Reusing cached layer
default/person-service-function-6c5dd-build-1-vd5zs-build-pod[build]:   Process types:
default/person-service-function-6c5dd-build-1-vd5zs-build-pod[build]:     function:           streaming-http-adapter java -cp /layers/io.projectriff.java/riff-invoker-java $JAVA_OPTS org.springframework.boot.loader.JarLauncher
default/person-service-function-6c5dd-build-1-vd5zs-build-pod[build]:     streaming-function: java -cp /layers/io.projectriff.java/riff-invoker-java $JAVA_OPTS org.springframework.boot.loader.JarLauncher
default/person-service-function-6c5dd-build-1-vd5zs-build-pod[build]:     web:                streaming-http-adapter java -cp /layers/io.projectriff.java/riff-invoker-java $JAVA_OPTS org.springframework.boot.loader.JarLauncher
default/person-service-function-6c5dd-build-1-vd5zs-build-pod[export]: Reusing layers from image 'gcr.io/fe-papicella/person-service@sha256:e684490fcfe75589a72df2c019c38d04fbaacf58bae6137c5a31b3c782ca2fee'
default/person-service-function-6c5dd-build-1-vd5zs-build-pod[export]: Reusing layer 'app'
default/person-service-function-6c5dd-build-1-vd5zs-build-pod[export]: Reusing layer 'config'
default/person-service-function-6c5dd-build-1-vd5zs-build-pod[export]: Reusing layer 'launcher'
default/person-service-function-6c5dd-build-1-vd5zs-build-pod[export]: Reusing layer 'org.cloudfoundry.openjdk:openjdk-jre'
default/person-service-function-6c5dd-build-1-vd5zs-build-pod[export]: Reusing layer 'io.projectriff.java:java-function'
default/person-service-function-6c5dd-build-1-vd5zs-build-pod[export]: Reusing layer 'io.projectriff.java:riff-invoker-java'
default/person-service-function-6c5dd-build-1-vd5zs-build-pod[export]: Reusing layer 'io.projectriff.java:streaming-http-adapter'
default/person-service-function-6c5dd-build-1-vd5zs-build-pod[export]: *** Images (sha256:1958396c267b616c3843ab8235a2078578553d26ba60469065246e7a2c7629b1):
default/person-service-function-6c5dd-build-1-vd5zs-build-pod[export]:       gcr.io/fe-papicella/person-service
default/person-service-function-6c5dd-build-1-vd5zs-build-pod[export]:       gcr.io/fe-papicella/person-service:b1.20191218.015754
default/person-service-function-6c5dd-build-1-vd5zs-build-pod[cache]: Caching layer 'org.cloudfoundry.openjdk:openjdk-jdk'
default/person-service-function-6c5dd-build-1-vd5zs-build-pod[cache]: Caching layer 'org.cloudfoundry.buildsystem:build-system-cache'
Function "person-service" is ready

```

4. Create a knative deployer as shown below

**riff knative deployer create knative-person-service --function-ref person-service --ingress-policy External --tail**

``` bash
$ riff knative deployer create knative-person-service --function-ref person-service --ingress-policy External --tail
Created deployer "knative-person-service"
default/knative-person-service-deployer-q5dxh-b8lrl-deployment-54chc9wh[user-container]:
default/knative-person-service-deployer-q5dxh-b8lrl-deployment-54chc9wh[user-container]:   .   ____          _            __ _ _
default/knative-person-service-deployer-q5dxh-b8lrl-deployment-54chc9wh[user-container]:  /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
default/knative-person-service-deployer-q5dxh-b8lrl-deployment-54chc9wh[user-container]: ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
default/knative-person-service-deployer-q5dxh-b8lrl-deployment-54chc9wh[user-container]:  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
default/knative-person-service-deployer-q5dxh-b8lrl-deployment-54chc9wh[user-container]:   '  |____| .__|_| |_|_| |_\__, | / / / /
default/knative-person-service-deployer-q5dxh-b8lrl-deployment-54chc9wh[user-container]:  =========|_|==============|___/=/_/_/_/
default/knative-person-service-deployer-q5dxh-b8lrl-deployment-54chc9wh[user-container]:  :: Spring Boot ::        (v2.2.1.RELEASE)
default/knative-person-service-deployer-q5dxh-b8lrl-deployment-54chc9wh[user-container]:
default/knative-person-service-deployer-q5dxh-b8lrl-deployment-54chc9wh[user-container]: 02:01:58.815 [main] INFO  i.p.invoker.main.EntryPoint - Starting EntryPoint on knative-person-service-deployer-q5dxh-b8lrl-deployment-54chc9wh with PID 14 (/layers/io.projectriff.java/riff-invoker-java/BOOT-INF/classes started by cnb in /workspace)

...

default/knative-person-service-deployer-q5dxh-b8lrl-deployment-54chc9wh[user-container]: 02:02:06.094 [main] INFO  o.s.c.f.d.FunctionArchiveDeployer - Registering function 'findPersonById' of type 'java.util.function.Function<java.lang.Long, java.lang.String>' in FunctionRegistry.
default/knative-person-service-deployer-q5dxh-b8lrl-deployment-54chc9wh[user-container]: 02:02:06.099 [main] INFO  o.s.c.f.d.FunctionArchiveDeployer - Registering function 'findByLastName' of type 'java.util.function.Function<java.lang.String, java.lang.String>' in FunctionRegistry.
default/knative-person-service-deployer-q5dxh-b8lrl-deployment-54chc9wh[user-container]: 02:02:06.099 [main] INFO  o.s.c.f.d.FunctionDeployerConfiguration - Successfully deployed archive: /workspace
default/knative-person-service-deployer-q5dxh-b8lrl-deployment-54chc9wh[user-container]: 02:02:06.197 [main] INFO  i.p.invoker.main.EntryPoint - Started EntryPoint in 8.016 seconds (JVM running for 9.085)
Deployer "knative-person-service" is ready

```

5. Let's inspect what functions and deployers we have just created as follows

``` bash
$ riff functions list
NAME             LATEST IMAGE                                                                                                 ARTIFACT    HANDLER          INVOKER   STATUS   AGE
person-service   gcr.io/fe-papicella/person-service@sha256:1958396c267b616c3843ab8235a2078578553d26ba60469065246e7a2c7629b1   <empty>     findPersonById   <empty>   Ready    7m1s
square           gcr.io/fe-papicella/square@sha256:3c95794e3628d9f26b155a35680ce3f969c1ee732d622477ea83e4f7ac5f81e3           square.js   <empty>          <empty>   Ready    15h

$ riff knative deployer list
NAME                     TYPE       REF              URL                                                 STATUS   AGE
knative-person-service   function   person-service   http://knative-person-service.default.example.com   Ready    3m6s
knative-square           function   square           http://knative-square.default.example.com           Ready    15h

```

6. Create a script to invoke the function service as shown below

``` bash
export HOST=knative-person-service.default.example.com
export INGRESS_IP=$(kubectl get svc istio-ingressgateway --namespace istio-system --output 'jsonpath={.status.loadBalancer.ingress[0].ip}')

echo "Host: $HOST "
echo "Function IP Address: $INGRESS_IP "
echo ""

http POST http://$INGRESS_IP "Content-Type: application/json" "Host: knative-person-service.default.example.com" --json <<< 1
http POST http://$INGRESS_IP "Content-Type: application/json" "Host: knative-person-service.default.example.com" --json <<< 2
```

7. Invoke the function as follows. I use https://httpie.org (httpie) as it's much easier then curl and it runs on windows, linux, mac

``` http request
$ ./run-person-service.sh
Host: knative-person-service.default.example.com
Function IP Address: 35.244.100.163

HTTP/1.1 200 OK
content-length: 60
content-type: text/plain; charset=utf-8
date: Wed, 18 Dec 2019 02:12:19 GMT
server: istio-envoy
x-envoy-upstream-service-time: 11506

"{\"id\":1,\"firstName\":\"pas\",\"lastName\":\"apicella\"}"

HTTP/1.1 200 OK
content-length: 62
content-type: text/plain; charset=utf-8
date: Wed, 18 Dec 2019 02:12:20 GMT
server: istio-envoy
x-envoy-upstream-service-time: 12

"{\"id\":2,\"firstName\":\"lucia\",\"lastName\":\"apicella\"}"
```

<hr />
Pas Apicella [papicella at pivotal.io] is an Advisory Platform Architect at Pivotal Australia