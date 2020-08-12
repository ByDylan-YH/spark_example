package scala_demo

/**
  * Author:BY
  * Date:2019/8/30
  * Description:构造器
  */
object ConstructDemo {
  def main(args: Array[String]) {
    //    val person = new Person("YH", 24);
    //    println(person.name + " : " + person.age);
    //
    //    val person2 = new Person("YH", 24, "Bingo");
    //    println(person2.name + " : " + person2.age + " : " + person2.company);

    val student = new Student("YH", 12, "TX");
    println(student.name + " : " + student.age + " : " + student.major);

    println(student);
  }
}

class Person(val name: String, val age: Int) {
  println("进入父类构造器");
  val gender = "male";
  var company: String = _;

  //附属构造器
  def this(name: String, age: Int, company: String) {
    //第一行代码必须要调用主构造器或者其它构造器
    this(name, age);
    this.company = company;
  }

  println("父类构造器完成...");
}

class Student(name: String, age: Int, var major: String) extends Person(name, age) {
  println("进入子类构造器");

  override val gender: String = "重写父类参数";

  override def toString: String = "重写父类toString方法," + gender;
  println("子类构造器完成...");
}