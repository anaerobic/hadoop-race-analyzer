#!/bin/bash

cd $HADOOP_PREFIX

#bin/hdfs dfsadmin -safemode leave

bin/hdfs dfs -mkdir /hdfs

bin/hdfs dfs -mkdir /hdfs/race

bin/hdfs dfs -copyFromLocal /share/race/data/results /hdfs/race/results

bin/hdfs dfs -rm -r /hdfs-output/race

bin/hadoop jar /share/race/hadoop-race-analyzer.jar app.AnalyzeRace /hdfs/race /hdfs-output/race

bin/hdfs dfs -cat /hdfs-output/race/*