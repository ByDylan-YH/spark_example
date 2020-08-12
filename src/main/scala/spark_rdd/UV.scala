package spark_rdd

import org.apache.spark.rdd.RDD;
import org.apache.spark.{SparkConf, SparkContext};

/**
  * Author:BY
  * Date:2020/4/13
  * Description:通过spark实现点击流日志分析 UV
  */
object UV {
  def main(args: Array[String]): Unit = {
    //1、构建SparkConf对象
    val sparkConf: SparkConf = new SparkConf().setAppName("UV").setMaster("local[2]");
    //2、构建SparkContext对象
    val sc = new SparkContext(sparkConf);
    sc.setLogLevel("warn");
    //3、读取数据文件
    val data: RDD[String] = sc.textFile("D:\\WorkSpace\\ideaProject\\spark_example\\doc\\agent.log");
    //4、获取用户的唯一标识 ip
    val ipRDD: RDD[String] = data.map(x => x.split(" ")(0));
    //5、ip去重
    val distinctRDD: RDD[String] = ipRDD.distinct();
    //6、统计uv
    val uv: Long = distinctRDD.count();
    println("uv: " + uv);
    sc.stop();
  }
}
