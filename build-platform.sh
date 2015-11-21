#!/usr/bin/env bash

./gradlew platform/circuitBreakerDashboard:build
./gradlew platform/configServer:build
./gradlew platform/serviceDiscovery:build