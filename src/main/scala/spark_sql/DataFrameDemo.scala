package spark_sql

import org.apache.spark.SparkContext
import org.apache.spark.sql.{Column, DataFrame, SparkSession}

/**
 * Author:BYDylan
 * Date:2020/4/18
 * Description:DataFrame API常用操作
 */
object DataFrameDemo extends App {
  private val spark: SparkSession = SparkSession.builder.master("local").appName("MyName").getOrCreate;
  private val sc: SparkContext = spark.sparkContext;

  //基本API
  private val df: DataFrame = spark.read.json("D:\\WorkSpace\\ideaProject\\spark_example\\doc\\people.json");
  // 使用printSchema方法输出DataFrame的Schema信息
  df.printSchema();
  // 使用select方法来选择我们所需要的字段
  df.select("name").show();
  // 使用select方法选择我们所需要的字段，并给age字段加1
  df.select(df("name"), (df("age") + 1).alias("age")).show();
  // 使用filter方法完成条件过滤
  df.filter(df("age") > 21).show();
  // 使用groupBy方法进行分组，求分组后的总数
  df.groupBy("age").count().show();
  //sql()方法执行SQL查询操作
//  df.registerTempTable("people");
  df.createTempView("people");
  spark.sql("SELECT * FROM people").show;

  //方式一：通过编程接口指定Schema
  case class Person(name: String, age: Int);
  val people = sc.textFile("D:\\WorkSpace\\ideaProject\\spark_example\\doc\\people.json");
  // 以字符串的方式定义DataFrame的Schema信息
  val schemaString = "name age";
  //导入所需要的类
  import org.apache.spark.sql.Row;
  import org.apache.spark.sql.types.{StringType, StructField, StructType};
  // 根据自定义的字符串schema信息产生DataFrame的Schema
  val schema = StructType(schemaString.split(" ").map(fieldName => StructField(fieldName, StringType, true)));
  //将RDD转换成Row
  val rowRDD = people.map(_.split(",")).map(p => Row(p(0), p(1).trim));
  // 将Schema作用到RDD上
  val peopleDataFrame = spark.createDataFrame(rowRDD, schema);
  println("peopleDataFrame: "+peopleDataFrame);
  // 将DataFrame注册成临时表
  peopleDataFrame.registerTempTable("people");
  val results = spark.sql("select name from people;");
  results.show;

  //方式二:通过RDD创建DataFrame
  import spark.implicits._;

  val rdd1 = sc.makeRDD(Seq(("a", 1),("b", 1)));
  val df1 = spark.createDataset(rdd1).toDF();
  df1.withColumn("name", new Column("_1")).withColumn("age", new Column("_2")).show();

  //方式三:方式二的简化
  val rdd2 = sc.makeRDD(Seq(("a", 1),("b", 1)));
  val df2 = spark.createDataset(rdd2).toDF("name", "age");
  df2.show();

}
