[![Build Status](https://travis-ci.org/mikegehard/user-management-evolution-kotlin.svg)](https://travis-ci.org/mikegehard/user-management-evolution-kotlin)

# Migration From Java to Kotlin

## Background
This repository demonstrates the evolution of a Java/SpringBoot based
application to a Kotlin/SpringBoot application.

## Running

### Prerequisites

* RabbitMQ must be running.
* Change platform/configServer/src/main/resources/application.yml to point to the right directory.

### Running the platform services

Use `./run-platform.sh` to run and `./stop-platform.sh` to stop.

### Running the applications

Use `./run-apps.sh` to run and `./stop-apps.sh` to stop.

### Running tests

1. Run the platform services
1. Run the applications
1. Run the tests

## Windows users

Substitute `gradlew.bat` for `./gradlew`.

## Tags

Tags will be used along the way to mark significant points in the evolution.

running-java - A running Java application complete with tests.
