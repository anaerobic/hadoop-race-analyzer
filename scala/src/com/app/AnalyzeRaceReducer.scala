package com.app


import org.apache.hadoop.mapreduce.{Mapper, Reducer}
import org.apache.hadoop.io.{LongWritable, IntWritable, Text}
import scala.collection.JavaConversions._

class AnalyzeRaceReducer extends Reducer[Text, IntWritable, Text, IntWritable] {
  val result = new IntWritable()

  type ReducerContext = Reducer[Text,IntWritable,Text,IntWritable]#Context

  override def reduce (key: Text, values: java.lang.Iterable[IntWritable], context: ReducerContext) {
    result.set(values.foldLeft(0) {_ + _.get()})
    context.write(key, result)
  }

}
