import org.apache.spark.{SparkConf, SparkContext};
import org.json4s.jackson.JsonMethods;

/**
 * Author:BYDylan
 * Date:2020/4/17
 * Description:SparkContext 操作json数据
 */
object ScJson {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Coalesce").set("spark.testing.memory", "2147480000");
    val sc = new SparkContext(conf);
    val file = sc.textFile("D:\\WorkSpace\\ideaProject\\spark_example\\doc\\people.json");
    file.collect().foreach(x => {
      println(JsonMethods.parse(x))
    });
  }
}
