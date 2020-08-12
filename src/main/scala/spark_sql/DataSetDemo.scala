package spark_sql

import org.apache.spark.sql.SparkSession;

/**
 * Author:BYDylan
 * Date:2020/4/18
 * Description:创建DataSet
 */
object DataSetDemo extends App {
  val spark = SparkSession.builder().master("local").appName("MyName").getOrCreate();
  val sc = spark.sparkContext;
  import spark.implicits._;
  //基本API
  val rdd = sc.makeRDD(List(("a", 1, 2), ("b", 4, 5), ("c", 6, 7)));
  var ds = spark.createDataset(rdd);
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
  val points1 = Seq(Point("bar", 3.0, 5.6), Point("foo", -1.0, 3.0)).toDS();
  val categories1 = Seq(Category(1, "foo"), Category(2, "bar")).toDS;
  points1.join(categories1, points1("label") === categories1("name")).show;

  //方式二:
    val pointsRDD = sc.parallelize(List(("bar", 3.0, 5.6), ("foo", -1.0, 3.0)));
    val categoriesRDD = sc.parallelize(List((1, "foo"), (2, "bar")));
    val points2 = pointsRDD.map(line => Point(line._1, line._2, line._3)).toDS;
    val categories2 = categoriesRDD.map(line => Category(line._1, line._2)).toDS;
    points2.join(categories2, points2("label") === categories2("name")).show;
}
