package scala_data_structure

import scala.io.StdIn

/**
 * Author:BYDylan
 * Date:2020/7/1
 * Description:栈
 */
object ArrayStackDemo {
  def main(args: Array[String]) {
    val stack = new ArrayStack(4);
    var key = "";
    while (true) {
      println();
      println("push:入栈");
      println("pop:出栈");
      println("peek:栈顶的值");
      println("list:遍历栈");
      key = StdIn.readLine();
      key match {
        case "push" =>
          println("请输入一个数: ");
          stack.push(StdIn.readInt());
        case "pop" => val value: Any = stack.pop();
          if (value.isInstanceOf[Exception]) {
            println(value.asInstanceOf[Exception].getMessage);
          } else {
            printf("取出栈顶值: %d", value);
          }
        case "peek" => printf("查看栈顶值: %d", stack.peek());
        case "list" => stack.list();
      }
    }
  }
}

class ArrayStack(arrMaxSize: Int) {
  val maxSize = arrMaxSize;
  //  创建数组
  private val array = new Array[Int](maxSize);
  //  栈顶
  var top = -1;

  //  判断栈满
  def isFull(): Boolean = {
    top == maxSize - 1;
  }

  //  判断栈空
  def isEmpty(): Boolean = {
    top == -1;
  }

  //  入栈
  def push(num: Int): Unit = {
    if (isFull()) {
      println("栈满");
      return
    }
    top += 1;
    array(top) = num;
  }

  //  出栈
  def pop(): Any = {
    if (isEmpty()) return new Exception("栈空");
    //    将top指向的值,保存到临时变量
    val res = array(top);
    top -= 1;
    return res;
  }

  //  查看栈顶的值
  def peek(): Any = {
    if (isEmpty()) return new Exception("栈空");
    return array(top);
  }

  //  遍历
  def list(): Unit = {
    if (isEmpty()) {
      println("栈空,无法遍历");
      return
    }
    //    逆序遍历
    for (i <- 0 to top reverse) {
      printf("arr(%d)=%d\n", i, array(i));
    }
  }

}