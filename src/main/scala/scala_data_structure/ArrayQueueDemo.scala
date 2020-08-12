package scala_data_structure

import scala.io.StdIn

/**
 * Author:BYDylan
 * Date:2020/6/15
 * Description:队列
 */
object ArrayQueueDemo {
  def main(args: Array[String]) {
    val queue = new ArrayQueue(3);
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
        case "show" => queue.showQueue();
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
      }
    }
  }

}

//编写一个数据结构的基本思路,创建-遍历-修改-删除
class ArrayQueue(arrMaxSize: Int) {
  //  指定队列的大小
  val maxSize = arrMaxSize;
  //  队列中的数据,存放在数组当中,即数组模拟队列
  val arr = new Array[Int](maxSize);
  //  front 初始化为-1,表示队列的头部,但约定不包含队列的头,即指向队列的第一个元素的前一个位置
  var front = -1;
  //  rear 初始化为-1.指向队列的尾部,包含最后这个元素
  var rear = -1;

  def isFull(): Boolean = {
    rear == maxSize - 1;
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
    //    将rear后移
    rear += 1;
    arr(rear) = num;
    printf("front: %d,rear: %d", front, rear);
  }

  //  遍历元素
  def showQueue(): Unit = {
    if (isEmpty()) {
      println("队列为空");
      return
    }
    //    第一个元素要+1,因为约定front表示第一个元素的前一个位置
    for (i <- front + 1 to rear) {
      printf("arr(%d): %d \t", i, arr(i));
    }
    printf("front: %d,rear: %d", front, rear);
  }

  //  可能取不到数据,抛异常
  def getQueue(): Any = {
    if (isEmpty()) {
      return new Exception("队列为空");
    }
    //    将front 后移
    front += 1;
    return arr(front);
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
