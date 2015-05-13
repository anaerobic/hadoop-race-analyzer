cd $HADOOP_PREFIX

#bin/hdfs dfsadmin -safemode leave

bin/hdfs dfs -mkdir /hdfs

bin/hdfs dfs -copyFromLocal /share/data/race-results.json /hdfs/race-results.json

bin/hdfs fs -rm -r /hdfs-output

bin/hadoop jar /share/hadoop-race-analyzer.jar app.AnalyzeRace /hdfs /hdfs-output

bin/hdfs dfs -cat /hdfs-output/*