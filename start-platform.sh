#!/usr/bin/env bash

./gradlew platform/circuitBreakerDashboard:bootRun > logs/hystrix.log 2>&1 &
./gradlew platform/configServer:bootRun > logs/config-server.log 2>&1 &
./gradlew platform/serviceDiscovery:bootRun > logs/service-discovery.log 2>&1 &
