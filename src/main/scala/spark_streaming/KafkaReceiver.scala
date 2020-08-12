package spark_streaming

import kafka.serializer.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream, ReceiverInputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext};

/**
 * Author:BYDylan
 * Date:2020/4/19
 * Description:SparkStream 通过 Receiver 操作 Kafka
 */
object KafkaReceiver {
  def main(args: Array[String]): Unit = {
    var sparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkStream").set("spark.testing.memory", "2147480000");
    val ssc = new StreamingContext(sparkConf, Seconds(3));
    //    Receiver方式: 可以直接写topic "zhaogw"
    val kafkaDsream: ReceiverInputDStream[(String, String)] = KafkaUtils.createStream(ssc, "192.168.1.101:2181", "zhaogw", Map("zhaogw" -> 3));
    //    Direct方式:
//    val kafkaParams: Map[String, String] = Map[String, String](
//      "bootstrap.servers" -> "192.168.1.101:2181",
//      "group.id" -> "group1",
//      "key.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer",
//      "value.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer");
    //    val kafkaDsream: InputDStream[(String, String)] = KafkaUtils.createDirectStream(ssc, kafkaParams, Set("zhaogw"));

//    窗口大小,滑动步长都应该是采集周期的整数倍
    kafkaDsream.window(Seconds(3),Seconds(3));
    val result: DStream[(String, Int)] = kafkaDsream.flatMap(t => t._2.split(" ")).map((_, 1)).reduceByKey(_ + _);
    result.print();
    ssc.start();
    ssc.awaitTermination();
  }
}
