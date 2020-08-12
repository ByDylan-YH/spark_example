package scala_demo

import sun.security.krb5

/**
 * Author:BYDylan
 * Date:2020/4/20
 * Description:单词统计
 */
object WordCount {
  def main(args: Array[String]) {
    val lines = Array("hello tom hello jerry", "hello tom hello tom", "hello world hello jerry");
    //切分然后压平
    val words: Array[String] = lines.flatMap(_.split(" "));
    //将单词和一放到一个元组中
    val wordAndOne: Array[(String, Int)] = words.map((_, 1));
    //分组
    val grouped: Map[String, Array[(String, Int)]] = wordAndOne.groupBy(_._1);
    //求Value的长度
    //grouped.map(t => (t._1, t._2.length))
    val wordAndCounts: Map[String, Int] = grouped.mapValues(_.length);
    //排序
    val result: List[(String, Int)] = wordAndCounts.toList.sortBy(_._2).reverse;
    //输出结果
    println(result);
  }
}
