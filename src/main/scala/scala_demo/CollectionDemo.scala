package scala_demo

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
 * Author:BYDylan
 * Date:2020/4/22
 * Description:集合操作
 */
object CollectionDemo {
  def main(args: Array[String]): Unit = {
    listDemo();
  }

  def listDemo(): Unit = {
    val demoList: List[String] = "Runoob" :: ("Google" :: ("Baidu" :: Nil));
    //    重复 3次
    val demoList1: List[String] = List.fill(3)("Runoob");
    var demoList2: List[String] = demoList ::: demoList1;
    //    通过给定的函数创建 6 个元素
    val demoList3 = List.tabulate(6)(n => n * n);
    val nums = Nil;

    println("demoList2 print ::: " + demoList2);
    println("demoList1 print fill: " + demoList1);
    println("demoList3 print tabulate: " + demoList3);
    println("First element: " + demoList2.head);
    println("Last element: " + demoList2.tail);
    println("is null: " + demoList2.isEmpty);
    println("Nil is empty: " + nums.isEmpty);

    //    java 与 scala 集合互转
    import collection.JavaConverters._;
    val asJava: java.util.List[String] = demoList.asJava;
    println("convertJavaList: " + asJava);
    val asScala: mutable.Buffer[String] = asJava.asScala;
    println("convertScala: " + asScala);

  }
}
