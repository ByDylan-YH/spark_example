package spark_rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Author:BY
 * Date:2020/4/13
 * Description:通过spark实现点击流日志分析 PV
 */
object PV {
  private val project_path: String = System.getProperty("user.dir")

  def main(args: Array[String]): Unit = {
    //1、构建SparkConf对象
    val sparkConf: SparkConf = new SparkConf().setAppName("PV").setMaster("local[2]")
    //2、构建SparkContext对象
    val sc = new SparkContext(sparkConf)
    sc.setLogLevel("warn")
    //3、读取数据文件
    val data: RDD[String] = sc.textFile(project_path + "\\doc\\agent.log")
    //4、统计pv
    val pv: Long = data.count()
    println("pv:" + pv)
    sc.stop()
  }
}
