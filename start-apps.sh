#!/usr/bin/env bash


./gradlew applications/billing:bootRun > logs/billing.log &
./gradlew applications/email:bootRun > logs/email.log &
./gradlew applications/ums:bootRun > logs/ums.log &
