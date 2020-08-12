package scala_data_structure

/**
 * Author:BYDylan
 * Date:2020/7/1
 * Description:迷宫
 */
object MiGone {
  def main(args: Array[String]) {
    //    创建迷宫,使用二维数组
    val map: Array[Array[Int]] = Array.ofDim[Int](8, 7);
    //    给map加墙
    //    给最上面和最下面设置1
    for (i <- 0 until 7) {
      map(0)(i) = 1;
      map(7)(i) = 1;
    }
    //    给最左面和最右面设置1
    for (i <- 0 until 8) {
      map(i)(0) = 1;
      map(i)(6) = 1;
    }
    for (row <- map) {
      for (item <- row) {
        printf("%d ", item)
      }
      println();
    }
    //    墙:
    map(3)(1) = 1;
    map(3)(2) = 1;
    //    map(2)(2) = 1;
    setWay(map, 1, 1);
    println("递归回溯后: ");
    for (row <- map) {
      for (item <- row) {
        printf("%d ", item)
      }
      println();
    }
  }

  /**
   * 功能:完成递归回溯找路
   *
   * @param map 地图
   * @param i   表示正在探测哪个位置的横坐标
   * @param j   表示正在探测哪个位置的纵坐标
   */
  def setWay(map: Array[Array[Int]], i: Int, j: Int): Boolean = {
    if (map(6)(5) == 2) { // 如果达到map(6)(5),则到达终点退出
      return true;
    } else {
      //      当前map(i)(j)取值0,1,2,3
      if (map(i)(j) == 0) { //没有探测
        //        使用自己定好的策略进行探测(下->右->上->左)
        //        先将map(i)(j)==2,假定它可以走通,但不一定
        map(i)(j) = 2;
        //        向下
        if (setWay(map, i + 1, j)) {
          return true;
        } else if (setWay(map, i, j + 1)) { // 右
          return true;
        } else if (setWay(map, i - 1, j)) { // 上
          return true;
        } else if (setWay(map, i, j - 1)) { // 左
          return true;
        } else {
          //          说明该点四个方向都无法到达终点
          map(i)(j) = 3;
          return false;
        }
      } else { // 1,2,3
        return false;
      }
    }
  }
}
