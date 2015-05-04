cd $HADOOP_PREFIX

bin/hdfs dfsadmin -safemode leave

bin/hadoop fs -rm -r /hdfs-output

bin/hadoop jar /share/hadoop-race-analyzer.jar app.AnalyzeRaceFromKafka /hdfs-output

bin/hdfs dfs -cat /hdfs-output/*