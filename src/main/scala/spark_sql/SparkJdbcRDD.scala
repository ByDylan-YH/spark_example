package spark_sql

import java.sql.DriverManager

import org.apache.spark.rdd.JdbcRDD
import org.apache.spark.{SparkConf, SparkContext};
/**
 * Author:BYDylan
 * Date:2020/4/18
 * Description:Spark自带方式连接
 */
object SparkJdbcRDD {
  def main(args: Array[String]): Unit = {
    val config = new SparkConf().setMaster("local[*]").setAppName("Map").set("spark.testing.memory", "2147480000");
    val sc = new SparkContext(config);
    val driver = "com.mysql.cj.jdbc.Driver";
    val url = "jdbc:mysql://localhost:3306/bingo";
    val userName = "root";
    val passWd = "By921644606";
    val JdbcRdd = new JdbcRDD(sc, () => {
      Class.forName(driver);
      DriverManager.getConnection(url, userName, passWd);
    },
      "select * from kafka where topic_partition >= ? and topic_partition <=?;",
      1,
      10,
      1,
      r => (r.getInt(1), r.getString(2))
    );

    println("JdbcRdd.count: " + JdbcRdd.count());
    JdbcRdd.foreach(println);
    sc.stop();
  }
}
