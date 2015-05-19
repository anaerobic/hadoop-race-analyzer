package com.app

import org.apache.hadoop.io.{DoubleWritable, LongWritable, Text}
import org.apache.hadoop.mapreduce.Mapper

import scala.util.parsing.json.JSON

class AnalyzeRaceMap extends Mapper[LongWritable, Text, Text, DoubleWritable] {

  type MapperContext = Mapper[LongWritable, Text, Text, DoubleWritable]#Context

  case class Result(groupName: String = "", bib: Double = 0, time: String = "", age: Double = 0)

  override def map(key: LongWritable, value: Text, context: MapperContext): Unit = {

    val json = JSON.parseFull(value.toString.split('\t')(1))

    json match {
      case Some(value) =>
        val map: Map[String, Any] = value.asInstanceOf[Map[String, Any]]
        val result = Result(
          groupName = map("groupName").asInstanceOf[String],
          bib = map("bib").asInstanceOf[Double],
          time = map("time").asInstanceOf[String],
          age = map("age").asInstanceOf[Double]
        )

        context.write(new Text(result.groupName), new DoubleWritable(result.age))
      case (None) =>
    }
  }

}
