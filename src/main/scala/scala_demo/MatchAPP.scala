package scala_demo

import scala.util.Random

/**
  * Author:BY
  * Date:2019/9/2
  * Description:模式匹配
  */
object MatchAPP extends App {
  val names = Array("aaa", "bbb", "ccc");
  matchDemo(names(Random.nextInt(names.length)), "hhh");
  matchArray(Array("aaa"));
  matchList(List("aaa", "3r2", "weqwe"));
  matchType(Map("name" -> "PK"));
  caseclassMatch(CTO("BY", 9));

  def matchDemo(name: String, other: String) {
    name match {
      case "aaa" => println("aaa");
      case "bbb" => println("bbb");
      case _ if (other == "hhh") => println("666");
      case _ => println("I Don't Know");
    }
  }

  def matchArray(array: Array[String]): Unit = {
    array match {
      case Array("aaa") => println("指定一个");
      case Array(x, y) => println("任意2个元素");
      case Array("aaa", _*) => println("包含aaa的多个元素");
      case _ => println("其它");
    }
  }

  def matchList(list: List[String]): Unit = {
    list match {
      case "aaa" :: Nil => println("指定一个");
      case x :: y :: Nil => println("任意2个元素");
      case "aaa" :: tail => println("aaa开头的多个元素");
      case _ => println("其它");
    }
  }

  def matchType(obj: Any): Unit = {
    obj match {
      case x: Int => println("Int类型");
      case s: String => println("String类型");
      case m: Map[_, _] => m.foreach(println);
      case _ => println("其它");
    }
  }

  def caseclassMatch(person: Person): Unit = {
    person match {
      case CTO(name, floor) => println("CTO name is " + name + ",floor is " + floor);
      case Employee(name, floor) => println("Employee name is " + name + ",floor is " + floor);
      case _ => println("other");
    }
  }

  class Person

  case class CTO(name: String, floor: Int) extends Person;

  case class Employee(name: String, floor: Int) extends Person;

  case class Other(name: String, floor: Int) extends Person;

}
