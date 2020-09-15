package spark_sql

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Dataset, SparkSession};

/**
 * Author:BYDylan
 * Date:2020/4/18
 * Description:创建DataSet
 */
object DataSetDemo extends App {
  private val sparkSession: SparkSession = SparkSession.builder().master("local").appName("MyName").getOrCreate();
  val sc = sparkSession.sparkContext;

  import sparkSession.implicits._;
  //基本API
  private val rdd: RDD[(String, Int, Int)] = sc.makeRDD(List(("a", 1, 2), ("b", 4, 5), ("c", 6, 7)));
  private val ds: Dataset[(String, Int, Int)] = sparkSession.createDataset(rdd);
  ds.show();
  ds.printSchema();

  // Dataset的方式打印
  ds.select("_1").collect();
  ds.select("_1").show();
  ds.select(ds("_1")).show();

  // 第二列+1, $"_2" + 1
  ds.select(($"_2" + 1).alias("column")).show();

  //通过case class创建DataSet
  case class Point(label: String, x: Double, y: Double);

  case class Category(id: Long, name: String);
  //方式一:
  private val points1: Dataset[Point] = Seq(Point("bar", 3.0, 5.6), Point("foo", -1.0, 3.0)).toDS();
  private val categories1: Dataset[Category] = Seq(Category(1, "foo"), Category(2, "bar")).toDS;
  points1.join(categories1, points1("label") === categories1("name")).show;

  //方式二:
  private val pointsRDD: RDD[(String, Double, Double)] = sc.parallelize(List(("bar", 3.0, 5.6), ("foo", -1.0, 3.0)));
  private val categoriesRDD: RDD[(Int, String)] = sc.parallelize(List((1, "foo"), (2, "bar")));
  private val points2: Dataset[Point] = pointsRDD.map(line => Point(line._1, line._2, line._3)).toDS;
  private val categories2: Dataset[Category] = categoriesRDD.map(line => Category(line._1, line._2)).toDS;
  points2.join(categories2, points2("label") === categories2("name")).show;
  sc.stop();
}
