package scala_demo

/**
  * Author:BY
  * Date:2019/9/2
  * Description:捕获异常
  */
object ExceptionAPP extends App {
  try {
    val i = 10 / 0;
    println(i);
  } catch {
    case e: ArithmeticException => println("捕获异常:除数不能为0");
    case e: Exception => println(e.getMessage);
  } finally {
    //释放资源
  }

}
