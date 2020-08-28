package spark_sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.{Dataset, Encoder, Encoders, Row, SparkSession, TypedColumn}
import org.apache.spark.sql.expressions.{Aggregator, MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, DoubleType, LongType, StructType}
import spark_sql.DataSetDemo.spark;

/**
 * Author:BYDylan
 * Date:2020/4/18
 * Description:自定义 强类型 聚合函数
 */
object SparkSqlUDAFClass {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("Coalesce").set("spark.testing.memory", "2147480000");
    val spark = SparkSession.builder.config(conf).getOrCreate();
    import spark.implicits._;
    val dataFrame = spark.read.json("D:\\WorkSpace\\ideaProject\\spark_example\\doc\\user.json");
    val udaf = new MyAvgFuntClass;
    //    将聚合函数转换成临时查询列
    val avgColumn: TypedColumn[UserBean, Double] = udaf.toColumn.name("avgage");
    val dataSet: Dataset[UserBean] = dataFrame.as[UserBean];
    dataSet.select(avgColumn).show();
    spark.stop();
  }
}
//注意这里的age,默认解析为 BigInt
case class UserBean(name: String, age: BigInt);

case class AvgBuffer(var sum: BigInt, var count: Int);

class MyAvgFuntClass extends Aggregator[UserBean, AvgBuffer, Double] {
  //  初始化
  override def zero: AvgBuffer = {
    AvgBuffer(0, 0);
  }

  //  聚合数据
  override def reduce(b: AvgBuffer, a: UserBean): AvgBuffer = {
    b.sum = b.sum + a.age;
    b.count = b.count + 1;
    return b;
  }

//  合并缓冲区
  override def merge(b1: AvgBuffer, b2: AvgBuffer): AvgBuffer = {
    b1.sum = b1.sum + b2.sum;
    b1.count = b1.count + b2.count;
    return b1;
  }

//  返回的值
  override def finish(reduction: AvgBuffer): Double = {
    reduction.sum.toDouble / reduction.count;
  }

  //  编码:基本上是固定的
  override def bufferEncoder: Encoder[AvgBuffer] = Encoders.product;

  //  编码:基本上是固定的
  override def outputEncoder: Encoder[Double] = Encoders.scalaDouble;
}
