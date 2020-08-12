package scala_demo

/**
 * Author:BY
 * Date:2019/8/30
 * Description:apply方法
 */
object ApplyDemo {
  def main(args: Array[String]) {
    for (i <- 1 to 10) {
      ApplyTest.incr;
    }
    println(ApplyTest.count);
    //    类名() ==> Object.apply
    val apply = ApplyTest();
    //    对象() ==> Class.apply
    val apply2 = new ApplyTest();
    //    println(apply2.apply());
    //    Class apply
    apply2();
  }
}

class ApplyTest {
  def apply() {
    println("Class apply");
  }
}

object ApplyTest {
  var count = 0;

  //最佳实践:在Object的apply方法中去new Class
  def apply() = {
    println("Object apply");

    //在Object中的apply中new class,最后一行默认是返回值
    new ApplyTest;
  }

  def incr = {
    count += 1;
  }
}