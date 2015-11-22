#!/usr/bin/env bash

mkdir -p logs/

./gradlew applications/billing:clean applications/billing:bootRun > logs/billing.log 2>&1 &
./gradlew applications/email:clean applications/email:bootRun > logs/email.log 2>&1 &
./gradlew applications/ums:clean applications/ums:bootRun > logs/ums.log 2>&1 &
