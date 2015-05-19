package com.app

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.{DoubleWritable, Text}
import org.apache.hadoop.mapreduce.Job
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat

object HdfsToPostgres {

  def main(args: Array[String]) {
    val conf: Configuration = new Configuration

    val job: Job = new Job(conf)

    job.setJarByClass(HdfsToPostgres.getClass)
    job.setMapperClass(classOf[HdfsToPostgresMap])
    job.setMapOutputKeyClass(classOf[Text])
    job.setMapOutputValueClass(classOf[Text])

    val outputPath: Path = new Path(args(2))

    FileInputFormat.addInputPath(job, new Path(args(1)))

    FileOutputFormat.setOutputPath(job, outputPath)
    
    job.waitForCompletion(true)
  }

}