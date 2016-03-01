#!/usr/bin/env bash

mkdir -p logs/applications

./gradlew applications/billing:clean applications/billing:bootRun > logs/applications/billing.log 2>&1 &
./gradlew applications/email:clean applications/email:bootRun > logs/applications/email.log 2>&1 &
./gradlew applications/ums:clean applications/ums:bootRun > logs/applications/ums.log 2>&1 &
