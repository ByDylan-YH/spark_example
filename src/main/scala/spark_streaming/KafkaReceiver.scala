package spark_streaming

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * Author:BYDylan
 * Date:2020/4/19
 * Description:SparkStream 通过 Receiver 操作 Kafka
 */
object KafkaReceiver {
  def main(args: Array[String]): Unit = {
    var sparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkStream").set("spark.testing.memory", "2147480000")
    val ssc = new StreamingContext(sparkConf, Seconds(3))
    //    Receiver方式: 可以直接写topic "zhaogw"
    //    val kafkaDsream: ReceiverInputDStream[(String, String)] = KafkaUtils.createStream(ssc, "192.168.1.101:2181", "zhaogw", Map("zhaogw" -> 3))
    //    Direct方式:
    val kafkaParams: Map[String, String] = Map[String, String](
      "bootstrap.servers" -> "192.168.1.101:2181",
      "group.id" -> "group1",
      "key.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer",
      "value.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer")
    val kafkaDsream: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](ssc, LocationStrategies.PreferConsistent, ConsumerStrategies.Subscribe[String, String](Set("zhaogw"), kafkaParams))
    //    窗口大小,滑动步长都应该是采集周期的整数倍
    kafkaDsream.window(Seconds(3), Seconds(3))
    val result: DStream[(String, Int)] = kafkaDsream.flatMap(t => t.key().toString.split(" ")).map((_, 1)).reduceByKey(_ + _)
    result.print()
    ssc.start()
    ssc.awaitTermination()
  }
}
