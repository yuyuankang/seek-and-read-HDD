#!/bin/bash
file=$1
iter=$2
for ((i = 3; i <= $#; i++)); do
  for ((a = 1; a <= $iter; a++)); do
    echo "test $a/$iter for ${!i}"
    sudo sh -c "echo 3 > /proc/sys/vm/drop_caches"
    sleep 3
    java -jar TestScan.jar test ${!i} >>temp_${!i}
    sleep 3
  done
done
pwd=$(pwd)
java -jar AnalyzeSeek.jar $file $pwd
