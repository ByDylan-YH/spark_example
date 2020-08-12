package scala_demo

import scala.io.Source;
import java.io.File;

/**
  * Author:BY
  * Date:2019/12/3
  * Description:
  */
object IODemo extends App {
  //  println(readLines("D:\\WorkSpace\\ideaProject\\scalaexample\\src\\main\\scala\\demo\\myfile.txt").foreach(x => println(x)));
  //  println(readLineReplace("D:\\WorkSpace\\ideaProject\\scalaexample\\src\\main\\scala\\demo\\myfile.txt").foreach(x => println(x)));
  //  println(readLineSplit("D:\\WorkSpace\\ideaProject\\scalaexample\\src\\main\\scala\\demo\\myfile.txt").foreach(x => println(x)));
  println(readLineFilter("D:\\WorkSpace\\ideaProject\\scalaexample\\src\\main\\scala\\demo\\myfile.txt").foreach(x => println(x)));

  def readLines(filePath: String): Array[String] = {
    val source = Source.fromFile(filePath);
    println("数据类型: " + source.getClass.getSimpleName);
    // 一行一行读取,并从最后一行读起
    return source.getLines.toArray.reverse;
  }

  def readLineReplace(filePath: String): Array[String] = {
    val lines: Array[String] = Source.fromFile(filePath).getLines.toArray
    return lines.map(_.replace(" ", "被替换"));
  }

  def readLineSplit(filePath: String): Array[String] = {
    Source.fromFile(filePath).mkString.split("\\s+").filter(_.length > 12);
  }

  def readLineFilter(filePath: String) = {
    Source.fromFile(filePath).mkString.split("\\s+") filter { s: String =>
      try {
        // 这是什么写法...
        s.toDouble;
        true;
      } catch {
        case _: Exception => false;
      }
    } map {
      _.toDouble;
    }
  }

  def no6(filePath: String) {
    val regex = "\"".r;
    Source.fromFile(filePath).getLines.filter(!regex.findFirstMatchIn(_).isEmpty).foreach(println _);
  }

  def no7(filePath: String) {
    val regex = """d+"""".r;
    Source.fromFile(filePath).mkString.split("\\s+").filter(!regex.findFirstMatchIn(_).isEmpty).foreach(println _);
  }

  def no8() {
    val regex = """<img\s+src="([^"]+)"""".r;
    val content = Source.fromURL("http://news.baidu.com/", "GBK").mkString
    println(content);
    for (regex(imgSrc) <- regex.findAllIn(content)) {
      println(imgSrc);
    }
  }

  def no9(file: File): Int = {
    var num = 0;
    file.listFiles() foreach {
      f: File =>
        if (f.isDirectory()) num += no9(f);
        else if (f.getPath().endsWith(".scala")) num += 1;
    }
    num;
  }
}
