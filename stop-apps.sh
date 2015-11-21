#!/usr/bin/env bash

# Using [] will prevent the grep process from showing up in the list.
apps=`ps | grep "[b]ootRun" | awk '{print $1}'`

for app in $apps; do
     kill $app > /dev/null
done
