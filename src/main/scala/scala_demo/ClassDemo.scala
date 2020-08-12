package scala_demo

/**
  * Author:BY
  * Date:2019/8/30
  * Description:类
  */
object ClassDemo {
  def main(args: Array[String]) {
    val person = new People();
    person.name = "YH";
    println(person.eat());
    person.watchFootball("soccer");

    //私有化对象,只能当前类访问
    //person.gender="男";
  }
}

class People {
  //占位符
  var name: String = _;
  val age: Int = 10;
  //这里的this也可以写包名,helloworld
  private[this] var gender: String = "male";

  def eat(): String = {
    name + " is eating...";
  }

  def watchFootball(teamName: String): Unit = {
    println(name + " is Watching " + teamName);
  }
}

