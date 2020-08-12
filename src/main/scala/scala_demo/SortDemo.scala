package scala_demo

import scala.collection.mutable.ListBuffer

/**
  * Author:BY
  * Date:2019/11/26
  * Description:几种排序
  */
object SortDemo {
  def main(args: Array[String]) {
    val list = List(2, 4, 5, 7, 6, 3, 1);
    val list_buffer = ListBuffer(2, 4, 5, 7, 6, 3, 1);
    println("排序前: " + list);
    //    println("冒泡排序: " + bubbleSort(list));
    //    println("选择排序: " + SelectSort(list_buffer));
//    println("插入排序: " + insertionSort(list));
    println("希尔排序: " + shellSort[Int](_ < _)(list_buffer));
    //        println("快速排序: " + fastSort(list));
    //    println("快速排序2: " + fastSort2(list));
        println("归并排序: " + mergedSort((x: Int, y: Int) => x < y)(list));
  }

  //冒泡排序
  def bubbleSort(list: List[Int]): List[Int] = {
    list match {
      case List() => List();
      //head:第一个元素 tail:其余所有元素
      case head :: tail => compute(head, bubbleSort(tail));
    }
  }

  def compute(data: Int, dataSet: List[Int]): List[Int] = dataSet match {
    case List() => List(data);
    case head :: tail => if (data <= head) data :: dataSet else head :: compute(data, tail);
  }

  //选择排序
  def SelectSort(list: ListBuffer[Int]): ListBuffer[Int] = {
    var minIndex = 0;
    var temp = 0;

    for (i <- 0 until (list.size)) {
      minIndex = i;
      for (j <- i + 1 until (list.size)) {
        if (list(j) < list(minIndex)) {
          minIndex = j;
        }
      }
      temp = list(i);
      list(i) = list(minIndex);
      list(minIndex) = temp;
    }
    return list;
  }

  //插入排序
  def insertionSort[T <% Ordered[T]](list: List[T]): List[T] = {
    def insert(e: T, list: List[T]): List[T] = {
      list match {
        case Nil => List(e);
        case head :: tail => if (head < e) head :: insert(e, tail) else e :: list;
      }
    }

    return list.foldLeft(List.empty[T])((head, tail) => insert(tail, head));
  }

  //希尔排序
  def shellSort[T](comparator: (T, T) => Boolean)(source: ListBuffer[T]): ListBuffer[T] = {
    var span = source.length / 2;
    while (span > 0) {
      for (i <- span until(source.length, span)) {
        for (j <- (span to(i, span)).reverse) {
          val current = source(j);
          val prev = source(j - span);
          if (comparator(current, prev)) {
            source(j - span) = current;
            source(j) = prev;
          }
        }
      }
      span = span / 2;
    }
    return source;
  }

  //快速排序
  def fastSort(list: List[Int]): List[Int] = {
    if (list.length < 2) {
      return list;
    } else {
      // 左边的值都要比head小,右边的值都要比head大,中间拼接head
      fastSort(list.filter(_ < list.head)) ++ list.filter(list.head == _) ++ fastSort(list.filter(_ > list.head));
    }
  }

  //快速排序第二种写法
  def fastSort2(list: List[Int]): List[Int] = {
    list match {
      //这是说如果是空就返回空吗?
      case Nil => Nil;
      case List() => List();
      case head :: tail =>
        val (left, right) = tail.partition(_ < head);
        fastSort2(left) ::: head :: fastSort2(right);
    }
  }

  //归并排序
  def mergedSort[T](less: (T, T) => Boolean)(list: List[T]): List[T] = {
    def merged(xList: List[T], yList: List[T]): List[T] = {
      (xList, yList) match {
        case (Nil, _) => yList;
        case (_, Nil) => xList;
        case (x :: xTail, y :: yTail) => {
          if (less(x, y)) x :: merged(xTail, yList);
          else
            y :: merged(xList, yTail);
        }
      }
    }

    val n = list.length / 2;
    if (n == 0) list;
    else {
      val (x, y) = list splitAt n;
      merged(mergedSort(less)(x), mergedSort(less)(y));
    }
  }
}
