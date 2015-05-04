package com.app

import scala.util.parsing.json.JSON
import org.apache.hadoop.io.{BytesWritable, IntWritable, LongWritable, Text}
import org.apache.hadoop.mapreduce.Mapper

class AnalyzeRaceFromKafkaMap extends Mapper[LongWritable, BytesWritable, Text, IntWritable] {

  private val word: Text = new Text()
  private val count: IntWritable = new IntWritable()

  type MapperContext = Mapper[LongWritable, BytesWritable, Text, IntWritable]#Context

  case class Result(groupName: String = "", bib: Double = 0, time: String = "", age: Double = 0)

  override def map(key: LongWritable, value: BytesWritable, context: MapperContext): Unit = {

    val json = new String(value.getBytes(), 0, value.getLength(), "UTF-8")

    def groupsToCounts = JSON.parseFull(json)
      .map(x => x.asInstanceOf[Map[String, Any]])
      .map(x => Result(groupName = x("groupName").asInstanceOf[String], bib = x("bib").asInstanceOf[Double], time = x("time").asInstanceOf[String], age = x("age").asInstanceOf[Double]))
      .groupBy(result => result.groupName)
      .mapValues(_.size)

    groupsToCounts.foreach { tuple =>
      word.set(tuple._1)
      count.set(tuple._2)
      context.write(word, count)
    }
  }
}
