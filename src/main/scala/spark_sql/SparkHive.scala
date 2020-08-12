package spark_sql

import org.apache.spark.sql.functions.regexp_extract;
import org.apache.spark.sql.{Column, SparkSession};

/**
  * Author:BY
  * Date:2019/11/1
  * Description:Spark操作Hive
  */
object SparkHive extends App {
  val spark = SparkSession.builder().config("hive.metastore.urls", "thrift://192.168.1.201:9083").enableHiveSupport().getOrCreate();
  val sc = spark.sparkContext;

  // 集成Hive后spark-shell下可直接访问Hive表
  val df = spark.table("toronto").select(regexp_extract(new Column("column"), "([\\w\\d]+)@\\w+.\\w+", 2)).alias("columnname");
  df.printSchema;
  df.show;

  //存入Hive表
  val df1 = spark.sql("select * from toronto");
  df1.filter("name = 'Michael' or age = 20").write.saveAsTable("t1");
}
