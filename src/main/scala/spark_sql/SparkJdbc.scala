package spark_sql

import org.apache.spark.sql.SparkSession;

/**
 * Author:BY
 * Date:2019/11/1
 * Description:Spark Dataframe方式连接
 */
object SparkJdbc extends App {
  val spark = SparkSession.builder.master("local[10]").appName("MyName").getOrCreate;
  val sc = spark.sparkContext;
  val url = "jdbc:mysql://localhost:3306/bingo?useSSL=false&characterEncoding=UTF-8";
  val tableName = "bingyi";
  val prop = new java.util.Properties;
  prop.setProperty("user", "root");
  prop.setProperty("password", "By921644606");
  prop.setProperty("driver", "com.mysql.cj.jdbc.Driver");
  // 取得该表数据
  val jdbcDF = spark.read.jdbc(url, tableName, prop);
  println(jdbcDF.select("Form").limit(3).collect().mkString);
  jdbcDF.select("Form").limit(3).foreach(name => println(name));
  //DF存为新的表
  //  jdbcDF.write.mode("append").jdbc(url, "tableName2", prop);
  sc.stop();
}
