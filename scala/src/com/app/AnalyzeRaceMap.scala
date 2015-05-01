package com.app

import scala.util.parsing.json.{JSONObject, JSON}
import org.apache.hadoop.io.{LongWritable, Text, IntWritable}
import org.apache.hadoop.mapreduce.{Mapper}

class AnalyzeRaceMap extends Mapper[LongWritable, Text, Text, IntWritable] {

  private val word: Text = new Text()
  private val count: IntWritable = new IntWritable()

  type MapperContext = Mapper[LongWritable, Text, Text, IntWritable]#Context

  case class Result(groupName: String = "", bib: Double = 0, time: String = "", age: Double = 0)

  override def map(key: LongWritable, value: Text, context: MapperContext): Unit = {

    def groupsToCounts = JSON.parseFull(value.toString())
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
