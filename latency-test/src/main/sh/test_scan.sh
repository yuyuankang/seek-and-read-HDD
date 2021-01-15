#!/bin/sh

# shellcheck disable=SC1068
file = $1
# shellcheck disable=SC2068
for i in ${@:2}; do
  for a in {1..100}; do
    sudo sh -c "echo 3 > /proc/sys/vm/drop_caches"
    sleep 3
    java -jar TestScan.jar file $i >>temp_$i
    sleep 3
  done
done

pwd=$(pwd)

java -jar AbalyzeSeek.jar file $pwd
