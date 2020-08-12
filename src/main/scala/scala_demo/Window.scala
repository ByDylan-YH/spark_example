package scala_demo

/**
 * Author:BYDylan
 * Date:2020/4/19
 * Description:窗口
 */
object Window {
  def main(args: Array[String]) {
    val list = List(1, 2, 3, 4, 5, 6);
    val iterator: Iterator[List[Int]] = list.sliding(3,3);
    for (result <- iterator) {
      println(result.mkString(","));
    }
  }
}
