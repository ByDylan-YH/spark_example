package scala_data_structure

/**
 * Author:BYDylan
 * Date:2020/7/1
 * Description:汉诺塔
 */
object HanoiTower {
  def main(args: Array[String]) {
    hanoiTower(3, 'A', 'B', 'C');
  }

  //  汉诺塔思路
  //  如果只有一个盘,a-c
  //  如果有2个盘,将看成2个部分,最下面盘,和上面盘
  //  将上面盘a->b,将下面盘a->c
  //  将b塔的所有移动到c
  def hanoiTower(nums: Int, a: Char, b: Char, c: Char): Unit = {
    //思路
    if (nums == 1) {
      println("第1个盘从" + a + " -> " + c);
    } else {
      hanoiTower(nums - 1, a, c, b); // 将nums - 1 从 a -> b , 中间借助 c
      println("第" + nums + "个盘从" + a + " -> " + c);
      hanoiTower(nums - 1, b, a, c); // 将nums - 1 从 b -> c , 中间借助 a
    }
  }
}
