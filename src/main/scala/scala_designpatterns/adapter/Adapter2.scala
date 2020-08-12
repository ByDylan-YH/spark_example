package scala_designpatterns.adapter

/**
  * Author:BY
  * Date:2019/12/9
  * Description:原有的快速排序和二分排序算法，和目标接口并不相符合
  */

// 抽象目标
trait ScoreOperation {
  def sort(array: Array[Int]): Array[Int];

  def search(array: Array[Int], key: Int): Int;
}

object QuickSort {
  def quickSort(array: Array[Int]): Array[Int] = {
    if (array.length <= 1) array
    else {
      val mid = array(array.length / 2);
      Array.concat(
        quickSort(array.filter(mid > _)),
        array.filter(mid == _),
        quickSort(array.filter(mid < _))
      );
    }
  }
}

object BinarySearch {
  def binarySearch(array: Array[Int], key: Int): Int = {
    var low = 0;
    var high = array.length - 1;
    while (low <= high) {
      val mid = (low + high) / 2;
      val midVal = array(mid);
      if (midVal < key) {
        low = mid + 1;
      } else if (midVal > key) {
        high = mid - 1;
      } else {
        //不能直接 1 ，否则将进入死循环
        return 1;
      }
    }
    return -1;
  }
}

// 适配器类
object OperationAdapter extends ScoreOperation {
  //快速排序，适配者
  private val sortObj = QuickSort;
  //二分查找，适配者
  private val searchObj = BinarySearch;

  //调用适配者快速排序的快速排序方法，实现接口的方法
  override def sort(array: Array[Int]) = sortObj.quickSort(array);

  //调用适配者二分查找的二分查找方法，实现接口的方法
  override def search(array: Array[Int], key: Int) = searchObj.binarySearch(array, key);
}

// 测试客户端
object Client2 extends App {
  //原数组
  val sources = Array(84, 76, 50, 69, 90, 91, 88, 86);
  //适配器接口
  val scoreOperation: ScoreOperation = OperationAdapter;
  //排序
  val result = scoreOperation.sort(sources);
  println("成绩排序输出");
  result.foreach(x => print(x + ","));
  var key = 90;
  println(s"查找成绩：$key");
  println(scoreOperation.search(result, key));
  if (scoreOperation.search(result, key) == 1) println(s"找到成绩$key") else println(s"没有找到成绩$key");
  key = 89
  println(s"查找成绩：$key");
  if (scoreOperation.search(result, key) == 1) println(s"找到成绩$key") else println(s"没有找到成绩$key");

}