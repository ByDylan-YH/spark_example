package spark_sql

import org.apache.spark.sql.SparkSession;

/**
 * Author:BYDylan
 * Date:2020/4/17
 * Description:spark 操作json数据
 */
object SparkJson {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder.master("local[*]").appName("MyName").getOrCreate;
    val sc = spark.sparkContext;
    val dataFrame = spark.read.json("D:\\WorkSpace\\ideaProject\\spark_example\\doc\\user.json");
    //    这种方式也可以,更通用
    //    val dataFrame = spark.read.format("json").load("D:\\WorkSpace\\ideaProject\\spark_example\\doc\\user.json");
    dataFrame.show();
    dataFrame.select(dataFrame("age") + 1).show();
    dataFrame.createTempView("student");
    spark.sql("select * from student").show();
  }
}