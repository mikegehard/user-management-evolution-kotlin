#!/usr/bin/env bash

# Using [] will prevent the grep process from showing up in the list.
processes=`ps | grep "[p]latform/.*:bootRun" | awk '{print $1}'`

for process in $processes; do
     kill $process > /dev/null
done
