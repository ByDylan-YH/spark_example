package scala_demo

/**
  * Author:BY
  * Date:2019/8/30
  * Description:循环
  */
object CirculateDemo {
  def main(args: Array[String]) {
    //    println(sum(x => x)(1)(5));
    println(no9(""));
    //    println(no10(5, -1));
  }

  def sum(f: Int => Int)(a: Int)(b: Int): Int = {

    @annotation.tailrec
    def loop(n: Int, acc: Int): Int = {
      if (n > b) {
        println(s"n=${n},acc=${acc}");
        acc;
      }
      else {
        println(s"n=${n},acc=${acc}");
        loop(n + 1, acc + f(n));
      }
    }

    loop(a, 0);
  }

  def no1(x: Int) = {
    if (x > 0) 1 else if (x < 0) -1 else 0
  }

  def no4(n: Int) {
    for (i <- n to 1 by -1) {
      print(i + " ");
    }
  }

  def no4_2(n: Int) {
    for (i <- new Range(n, 0, -1)) yield {
      print(i + " ");
    }
  }

  def countdown(n: Int) {
    for (i <- n to 1 by -1) {
      print(i + " ");
    }
  }

  def countdown_2(n: Int) {
    for (i <- new Range(n, 0, -1)) yield {
      print(i + " ");
    }
  }

  def no6(str: String): Long = {
    var result: Long = 1;
    for (c <- str) {
      result *= c.toInt;
    }
    return result;
  }

  def no7(str: String): Long = {
    var result: Long = 1;
    str.foreach(result *= _.toInt);
    return result;
  }

  def product: String => Long = no7;

  def no9(str: String): Long = {
    def no9_recursion(strTail: String, acc: Long): Long = {
      if (strTail.isEmpty)
        return acc;
      else no9_recursion(strTail.tail, acc * strTail.head.toInt);
    }

    no9_recursion(str, 1);
  }

  def no10(x: Int, n: Int): Int = {
    if (n == 0) 1;
    else if (n < 0) 1 / no10(x, -n);
    else if (n % 2 == 0) no10(x, n / 2) * no10(x, n / 2);
    else x * no10(x, n - 1);
  }
}
