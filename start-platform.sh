#!/usr/bin/env bash

mkdir -p logs/

java -jar platform/circuitBreakerDashboard/build/libs/hystrix-dashboard-0.0.1-SNAPSHOT.jar > logs/hystrix.log 2>&1 &
java -jar platform/configServer/build/libs/config-server-0.0.1-SNAPSHOT.jar > logs/config-server.log 2>&1 &
java -jar platform/serviceDiscovery/build/libs/service-discovery-0.0.1-SNAPSHOT.jar > logs/service-discovery.log 2>&1 &
