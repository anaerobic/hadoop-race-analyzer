cd $HADOOP_PREFIX

bin/hdfs dfsadmin -safemode leave

bin/hadoop dfs -mkdir /hdfs

bin/hadoop dfs -copyFromLocal /share/data/race-results.json /hdfs/race-results.json

bin/hadoop fs -rm -r /hdfs-output

bin/hadoop jar /share/hadoop-race-analyzer.jar app.AnalyzeRace /hdfs /hdfs-output

bin/hdfs dfs -cat /hdfs-output/*