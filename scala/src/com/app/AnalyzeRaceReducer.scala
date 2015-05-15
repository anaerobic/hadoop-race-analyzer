package com.app

import org.apache.hadoop.io.{DoubleWritable, Text}
import org.apache.hadoop.mapreduce.Reducer

import scala.collection.JavaConversions._

class AnalyzeRaceReducer extends Reducer[Text, DoubleWritable, Text, DoubleWritable] {

  type ReducerContext = Reducer[Text, DoubleWritable, Text, DoubleWritable]#Context

  override def reduce(key: Text, values: java.lang.Iterable[DoubleWritable], context: ReducerContext) {

    val ages = values.map(x => x.get)

    val aveAge = Math.round(ages.foldLeft(0.0)((acc, y) => acc + y) / ages.foldLeft(0.0)((r, c) => r + 1))

    context.write(key, new DoubleWritable(aveAge))
  }

}
