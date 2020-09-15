package spark_sql

import org.apache.spark.{SparkConf, SparkContext}

/**
 * Author:BY
 * Date:2020/4/17
 * Description:保存文件
 */
 object SaveAs {
  def main(args: Array[String]) {
    val conf: SparkConf = new SparkConf().setMaster("local").setAppName("reduce").set("spark.testing.memory", "2147480000");
    val sc = new SparkContext(conf);
    val foldRdd = sc.makeRDD(1 to 10, 2);
    foldRdd.saveAsTextFile("D:\\WorkSpace\\ideaProject\\spark_example\\doc\\saveAsTextFile\\");
    foldRdd.saveAsObjectFile("D:\\WorkSpace\\ideaProject\\spark_example\\doc\\saveAsObjectFile\\");
    sc.stop();
  }
}
