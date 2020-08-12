package scala_demo

import scala.collection.mutable.ArrayBuffer

/**
 * Author:BYDylan
 * Date:2020/4/21
 * Description:隐式值
 */
object Implicit {
  def main(args: Array[String]): Unit = {
    //    隐式值
    implicit val str1: String = "jack";
    //      implicit val str1: Int = 20;
    def hello(implicit name: String = "默认值"): Unit = {
      println(name + " hello");
    }
    //    使用隐式值不要带
    hello;
    hello("s");
    val arr2 = ArrayBuffer[Int]()
  }
}
