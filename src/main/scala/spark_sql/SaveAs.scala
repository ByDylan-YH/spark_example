package spark_sql

import org.apache.spark.{SparkConf, SparkContext}

/**
 * Author:BY
 * Date:2020/4/17
 * Description:保存文件
 */
object SaveAs {
  private val project_path: String = System.getProperty("user.dir")

  def main(args: Array[String]) {
    val conf: SparkConf = new SparkConf().setMaster("local").setAppName("reduce").set("spark.testing.memory", "2147480000")
    val sc = new SparkContext(conf)
    val foldRdd = sc.makeRDD(1 to 10, 2)
    foldRdd.saveAsTextFile(project_path + "\\doc\\saveAsTextFile\\")
    foldRdd.saveAsObjectFile(project_path + "\\doc\\saveAsObjectFile\\")
    sc.stop()
  }
}
