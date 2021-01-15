#!/bin/sh
# shellcheck disable=SC2066
for i in "$*"; do
  echo $i
done

for i in "$*"; do
  for a in {1..100}; do
    sudo sh -c "echo 3 > /proc/sys/vm/drop_caches"
    sleep 3
    java -jar JMHTests.jar $i >>temp_$i
    sleep 3
  done
done
