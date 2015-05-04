package com.app

import com.conductor.kafka.hadoop.KafkaInputFormat
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.hadoop.mapreduce.Job
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat

object AnalyzeRaceFromKafka {

  def main(args: Array[String]): Unit = {
    val conf: Configuration = new Configuration

    val job: Job = Job.getInstance(conf, "fromKafka")

    job.setJarByClass(AnalyzeRaceFromKafka.getClass)

    job.setInputFormatClass(classOf[KafkaInputFormat])

    KafkaInputFormat.setZkConnect(job, "zookeeper.lacolhost.com:2181")

    KafkaInputFormat.setTopic(job, "results")

    job.setMapperClass(classOf[AnalyzeRaceFromKafkaMap])

    job.setReducerClass(classOf[AnalyzeRaceReducer])

    job.setCombinerClass(classOf[AnalyzeRaceReducer])

    job.setMapOutputKeyClass(classOf[Text])

    job.setMapOutputValueClass(classOf[IntWritable])

    val outputPath: Path = new Path(args(1))

    FileOutputFormat.setOutputPath(job, outputPath)

    outputPath.getFileSystem(conf).delete(outputPath, true)

    job.waitForCompletion(true)
  }
}
