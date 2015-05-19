# hadoop-race-analyzer
A Hadoop workload for analyzing race results generated from https://github.com/anaerobic/generaterace

Originally based on https://github.com/anaerobic/hadoop-example

##Usage for map-reduce-test:

0. Build the map-reduce-test-1.0.jar by cd'ing to that directory and using 'mvn package' from the command line.
1. Copy map-reduce-test-1.0.jar to the hadoop/share/race directory
2. Copy the hadoop directory to ~/hadoop on your host machine
3. Run sh ~/hadoop/docker-up.sh
4. Run sh share/race/map-reduce-test.sh from inside the Hadoop container
5. ᕕ( ᐛ )ᕗ

##Usage for hdfs-to-postgres:

Spin up PostgreSQL in Docker with https://github.com/anaerobic/docker-postgres-test

Add a foo table using:
```sql
CREATE TABLE public.foo
(
   id serial, 
   some_json json
) 
WITH (
  OIDS = FALSE
)
;
```

0. Build the hdfs-to-postgres-1.0.jar by cd'ing to that directory and using 'mvn package' from the command line.
1. Copy hdfs-to-postgres-1.0.jar to the hadoop/share/race directory
2. Copy the hadoop directory to ~/hadoop on your host machine
3. Run sh ~/hadoop/docker-up.sh
4. Run sh share/race/hdfs-to-postgres.sh from inside the Hadoop container
5. (╯°□°）╯︵ ┻━┻
