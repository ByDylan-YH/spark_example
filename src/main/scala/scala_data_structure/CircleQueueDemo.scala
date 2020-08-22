package scala_data_structure

import scala.io.StdIn;

/**
 * Author:BYDylan
 * Date:2020/6/15
 * Description:环形队列
 */
object CircleQueueDemo {
  def main(args: Array[String]) {
    val queue = new CircleQueue(3);
    var key = "";
    while (true) {
      println();
      println("请选择菜单");
      println("show: 显示队列");
      println("add: 添加元素");
      println("get: 获取元素");
      println("peek: 查看头元素");
      println("exit: 退出程序");
      key = StdIn.readLine();
      key match {
        case "show" => queue.showQueue();
        case "add" =>
          println("请输入一个数");
          val num = StdIn.readInt();
          queue.addQueue(num);
        case "get" =>
          //          对取回的值进行判断
          val result: Any = queue.getQueue();
          if (result.isInstanceOf[Exception]) {
            println(result.asInstanceOf[Exception].getMessage);
          } else {
            printf("取回值:%d", result);
          }
        case "peek" =>
          //          对取回的值进行判断
          val result: Any = queue.peek();
          if (result.isInstanceOf[Exception]) {
            println(result.asInstanceOf[Exception].getMessage);
          } else {
            printf("队列当前头元素:%d", result);
          }
        case "exit" => return;
        case _ => println("没有这个选项,请重新输入!");
      }
    }
  }

}

//编写一个数据结构的基本思路,创建-遍历-修改-删除
class CircleQueue(arrMaxSize: Int) {
  //  指定队列的大小
  val maxSize = arrMaxSize;
  //  队列中的数据,存放在数组当中,即数组模拟队列
  val arr = new Array[Int](maxSize);
  //  front 初始化为0,表指向队列的第一个元素
  var front = 0;
  //  rear 初始化为0.指向队列最后这个元素的后一个位置
  var rear = 0;

  def isFull(): Boolean = {
    //    rear == maxSize - 1;
    (rear + 1) % maxSize == front;
  }

  def isEmpty(): Boolean = {
    rear == front;
  }

  //  添加元素
  def addQueue(num: Int): Unit = {
    if (isFull()) {
      println("队列已满,无法加入");
      return
    }
    arr(rear) = num;
    //    将rear后移
    rear = (rear + 1) % maxSize;
    printf("front: %d,rear: %d", front, rear);
  }

  //  遍历元素
  //  1.从front打印,打印多少个元素
  //  2.所以需要统计出有效元素个数
  def showQueue(): Unit = {
    if (isEmpty()) {
      println("队列为空");
      return
    }
    // 这里使用取模的方式
    for (i <- front until front + size()) {
      printf("arr(%d): %d \t", i % maxSize, arr(i % maxSize));
    }

    //  编写一个方法,统计当前有多少个元素
    def size(): Int = {
      return (rear + maxSize - front) % maxSize;
    }

    printf("front: %d,rear: %d", front, rear);
  }

  //  可能取不到数据,抛异常
  def getQueue(): Any = {
    if (isEmpty()) {
      return new Exception("队列为空");
    }
    //    因为front指向队列的第一个元素,先保存到临时变量
    val res: Int = arr(front);
    front = (front + 1) % maxSize;
    return res;
  }

  //  查看队列的头元素,不取出
  def peek(): Any = {
    if (isEmpty()) {
      return new Exception("队列为空");
    }
    //    front 不要动
    return arr(front + 1);
  }
}
