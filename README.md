# hadoop-race-analyzer
A Hadoop workload for analyzing race results generated from https://github.com/anaerobic/generaterace

Based on https://github.com/anaerobic/hadoop-example

Usage:

0. Build the java .jar (good luck with that; I used IntelliJ) and call it hadoop-race-analyzer.jar
1. Copy that hadoop-race-analyzer.jar to the docker\share directory
2. Copy the docker directory to ~/docker on your host machine
3. Run sh ~/docker/docker-up.sh
4. Run sh share/race-analyzer.sh from inside the Hadoop container
5. ᕕ( ᐛ )ᕗ