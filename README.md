# hadoop-race-analyzer
A Hadoop workload for analyzing race results generated from https://github.com/anaerobic/generaterace

Based on https://github.com/anaerobic/hadoop-example

Usage:

0. Build the map-reduce-test-1.0.jar using 'mvn package' from the command line.
1. Copy map-reduce-test-1.0.jar to the hadoop/share/race directory
2. Copy the hadoop directory to ~/hadoop on your host machine
3. Run sh ~/hadoop/docker-up.sh
4. Run sh share/race/map-reduce-test.sh from inside the Hadoop container
5. ᕕ( ᐛ )ᕗ
