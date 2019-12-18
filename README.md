# Spring Boot JPA / RIFF Function Demo

This is a basic demo showing how to use a riff function which is a Spring Boot application using 
Spring Data JPA. In this example we deploy the function to riff 0.5.0. The database used here is an 
embedded H2, but you could easily use an external database if required

## Steps

1. Ensure you have installed riff. Use the guide below to get started with riff 

https://projectriff.io/docs/v0.5/getting-started

```
$ riff --version
riff version 0.5.0-snapshot (668e2c9ee2458f0f7a07761a5690ffac534c3914)
```

2. Ensure you have installed riff into a k8s cluster of your choice. I am using GKE cluster which you can 
also do by following this guide

https://projectriff.io/docs/v0.5/getting-started/gke

You will be ready once you run this command showing the status of the install

```
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

```
```

4. 

<hr />
Pas Apicella [papicella at pivotal.io] is an Advisory Platform Architect at Pivotal Australia