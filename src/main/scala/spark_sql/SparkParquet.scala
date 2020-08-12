package spark_sql

import org.apache.spark.sql.types._
import org.apache.spark.sql.{Row, SparkSession};

/**
 * Author:BYDylan
 * Date:2020/4/19
 * Description:Spark SQL读写parquet文件
 */
object SparkParquet extends App {
  val spark = SparkSession.builder.master("local").appName("MyName").getOrCreate;
  val sc = spark.sparkContext;
  val schema = StructType(Array(StructField("name", StringType),
    StructField("favorit_color", StringType),
    StructField("favorite_numbers", ArrayType(IntegerType))));
  val rdd = sc.parallelize(List(("Alyssa", null, Array(3, 9, 15, 20)), ("Ben", "red", null)));
  val rowRDD = rdd.map(p => Row(p._1, p._2, p._3));
  val df = spark.createDataFrame(rowRDD, schema);
  df.write.parquet("D:\\WorkSpace\\ideaProject\\spark_example\\doc");
  //  通用保存方式
  //  df.write.format("json").mode("overwrite").save("D:\WorkSpace\ideaProject\spark_example\doc");

  val df1 = spark.read.parquet("D:\\WorkSpace\\ideaProject\\spark_example\\doc");
  df1.show;
  df1.printSchema;
}
