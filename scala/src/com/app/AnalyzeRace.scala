package com.app

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.mapreduce.Job
import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.hadoop.fs.Path
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat
import java.util

object AnalyzeRace {

  def main(args: Array[String]) {
    val conf: Configuration = new Configuration

    val job: Job = new Job(conf)

    job.setJarByClass(AnalyzeRace.getClass)
    job.setMapperClass(classOf[AnalyzeRaceMap])
    job.setReducerClass(classOf[AnalyzeRaceReducer])
    job.setCombinerClass(classOf[AnalyzeRaceReducer])
    job.setMapOutputKeyClass(classOf[Text])
    job.setMapOutputValueClass(classOf[IntWritable])

    val outputPath: Path = new Path(args(2))

    FileInputFormat.addInputPath(job, new Path(args(1)))

    FileOutputFormat.setOutputPath(job, outputPath)
    outputPath.getFileSystem(conf).delete(outputPath, true)
    job.waitForCompletion(true)
  }

}