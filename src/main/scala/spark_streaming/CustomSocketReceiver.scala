package spark_streaming

import java.io.{BufferedReader, InputStreamReader}
import java.net.Socket
import java.nio.charset.StandardCharsets

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.receiver.Receiver

/**
 * Author:BYDylan
 * Date:2020/4/19
 * Description:自定义数据源,实现监控某个端口号,获取该端口号内容
 */
object CustomSocketReceiver {
  def main(args: Array[String]) {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("StreamWordCount");
    val ssc = new StreamingContext(sparkConf, Seconds(5));
    //    创建自定义receiver的Streaming
    val lineStream = ssc.receiverStream(new CustomSocketReceiver("127.0.0.1", 9999))
    val wordAndCountStreams = lineStream.flatMap(_.split("\t")).map((_, 1)).reduceByKey(_ + _);
    wordAndCountStreams.print();
    ssc.start();
    ssc.awaitTermination();
  }
}

class CustomSocketReceiver(host: String, port: Int) extends Receiver[String](StorageLevel.MEMORY_ONLY) {
  //  启动时,读数据并将数据发送给Spark
  override def onStart(): Unit = {
    new Thread("Socket Receiver") {
      override def run() {
        receive();
      }
    }.start();
  }

  //  读数据并将数据发送给Spark
  def receive(): Unit = {
    var socket: Socket = new Socket(host, port);
    var input: String = null;
    val reader = new BufferedReader(new InputStreamReader(socket.getInputStream, StandardCharsets.UTF_8));
    input = reader.readLine();
    //    当receiver没有关闭并且输入数据不为空,则循环发送数据给Spark
    while (!isStopped() && input != null) {
      store(input);
      input = reader.readLine();
    }
    reader.close();
    socket.close();
    restart("restart");
  }

  override def onStop(): Unit = ???
}
