package com.app

import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.Reducer
import scala.collection.JavaConversions._
import scala.util.parsing.json.{JSON, JSONObject}

class AnalyzeRaceReducer extends Reducer[Text, Text, Text, Text] {

  type ReducerContext = Reducer[Text, Text, Text, Text]#Context

  case class Result(groupName: String = "", time: String = "", age: Double = 0)

  override def reduce(key: Text, values: java.lang.Iterable[Text], context: ReducerContext) {

    val results = values.map(x => JSON.parseFull(x.toString()))
      .map(x => x.asInstanceOf[Map[String, Any]])
      .map(x => Result(groupName = x("groupName").asInstanceOf[String], time = x("time").asInstanceOf[String], age = x("age").asInstanceOf[Double]))

    val aveAge = results.foldLeft(0.0)((acc, y) => acc + y.age) / results.foldLeft(0.0)((r, c) => r + 1)

    val maxAge = results.maxBy(x => x.age).age

    val minAge = results.minBy(x => x.age).age

    val json = JSONObject(Map("minAge" -> minAge, "maxAge" -> maxAge, "aveAge" -> aveAge))

    context.write(key, new Text(json.toString()))
  }

}
