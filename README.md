# hadoop-race-analyzer
A Hadoop workload for analyzing race results generated from https://github.com/anaerobic/generaterace

Based on https://github.com/anaerobic/hadoop-example

Usage:

0. Build the java .jar (good luck with that; I used IntelliJ) and call it hadoop-race-analyzer.jar
1. Copy that hadoop-race-analyzer.jar to the hadoop/share/race directory
2. Copy the hadoop directory to ~/hadoop on your host machine
3. Run sh ~/hadoop/docker-up.sh
4. Run sh share/race/analyze-race.sh from inside the Hadoop container
5. ᕕ( ᐛ )ᕗ
