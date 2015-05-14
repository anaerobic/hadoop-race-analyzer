package com.app

import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.hadoop.mapreduce.Mapper
import scala.util.parsing.json.{JSON, JSONObject}

class AnalyzeRaceMap extends Mapper[IntWritable, Text, Text, Text] {

  type MapperContext = Mapper[IntWritable, Text, Text, Text]#Context

  case class Result(groupName: String = "", bib: Double = 0, time: String = "", age: Double = 0)

  override def map(key: IntWritable, value: Text, context: MapperContext): Unit = {

    JSON.parseFull(value.toString().split('\t')(1))
      .map(x => x.asInstanceOf[Map[String, Any]])
      .map(x => Result(groupName = x("groupName").asInstanceOf[String], bib = x("bib").asInstanceOf[Double], time = x("time").asInstanceOf[String], age = x("age").asInstanceOf[Double]))
      .foreach(x => {
        val json = JSONObject(Map("time" -> x.time, "age" -> x.age))
        context.write(new Text(x.groupName), new Text(json.toString()))
      })
  }

}
