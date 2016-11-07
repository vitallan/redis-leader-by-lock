# redis-leader-by-lock
Simple implementation of Cluster Leadership Election using redis lock

Using Spring-Boot and Redis only

## Motivation
Almost all examples of Leadership Election using Spring Boot go to Spring Cloud Cluster in Hazelcast (now deprecated) and Zookeeper(overpower in simple cases), and that's kinda strange, since [Redis can use Set Lock](http://redis.io/topics/distlock) to create an almost trivial Leadership Election. That's why I did it.

## Requirements
Java 8

Maven

Redis

## Running
mvn clean install

(in four different terminals, so you can see the outputs)

java -jar target/redis-leader-by-lock-0.0.1-SNAPSHOT.jar --server.port=8081

java -jar target/redis-leader-by-lock-0.0.1-SNAPSHOT.jar --server.port=8082

java -jar target/redis-leader-by-lock-0.0.1-SNAPSHOT.jar --server.port=8083

java -jar target/redis-leader-by-lock-0.0.1-SNAPSHOT.jar --server.port=8084


The console log will (hopefully) tell you what's happening

###Things to look
Almost everything is happening in LeaderWrapper. Take a look at the source code, it's really simple

#Important

Please, oh please, if I did anything wrong, let me know and I will fix it
