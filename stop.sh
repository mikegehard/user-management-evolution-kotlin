#!/usr/bin/env bash

./stop-apps.sh && ./stop-platform.sh

rm -rf logs/
mkdir -p logs/