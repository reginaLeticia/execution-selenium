#!/bin/bash

num_execution=$1

for (( i = 0; i <= $num_execution; i++ ))
do
	./run-docker.sh -p yes -n collabtive-0
	sleep 5
	./run-testsuite.sh
	./teardown-docker.sh collabtive-0
	sleep 2
	cp test-output/emailable-report.html test-output/emailable-report-$i.html
	killall chromedriver
	killall chrome
	sleep 1
done