package scala_data_structure

import scala.collection.mutable.ArrayBuffer

/**
 * Author:BYDylan
 * Date:2020/5/29
 * Description:稀疏矩阵
 */
object SparseArrayDemo {
  def main(args: Array[String]) {
    val row = 11;
    val col = 11;
    val chessMap: Array[Array[Int]] = Array.ofDim[Int](row, col);
    //    初始化
    chessMap(1)(2) = 1; // 第一颗棋子
    chessMap(2)(3) = 2; // 第二颗棋子
    println("原始棋盘: ");
    for (row <- chessMap) {
      for (item <- row) {
        printf("%d ", item);
      }
      //      换行
      println();
    }

    //    对原始的数据进行压缩
    //    1.创建一个ArrayBuffer,可以动态加入数据
    val sparseArray: ArrayBuffer[Node] = ArrayBuffer[Node]();
    //    2.使用node对象,表示一行数据
    sparseArray.append(new Node(row, col, 0));
    //    遍历 chessMap ,如果发现非0的值就创建一个node对象,并加入到 sparseArray
    var x = 0;
    for (i <- 0 until chessMap.length) {
      for (j <- 0 until chessMap(i).length) {
        if (chessMap(i)(j) != 0) { // 有效数据需要保存
          x += 1;
          sparseArray.append(new Node(i, j, chessMap(i)(j)));
        }
      }
      sparseArray.remove(0);
      sparseArray.insert(0, new Node(row, col, x));
    }
    println("稀疏数组:");
    for (i <- 0 until sparseArray.length) {
      val node: Node = sparseArray(i);
      printf("%d %d %d\n", node.row, node.col, node.value);
    }
    //    将稀疏数组恢复成原始数组
    //    1.读取稀疏数组的第一行创建一个二维棋盘
    //    2.从第二行开始遍历,每读取到一个node就将对应的值恢复
    val node: Node = sparseArray(0);
    val chessMap2: Array[Array[Int]] = Array.ofDim[Int](node.row, node.col);
    for (i <- 1 until sparseArray.length) {
      val node1: Node = sparseArray(i);
      chessMap2(node1.row)(node1.col) = node1.value;
    }
    println("恢复后的棋盘:");
    for (row <- chessMap2) {
      for (item <- row) {
        printf("%d ", item);
      }
      //      换行
      println();
    }
  }
}

class Node(val row: Int, val col: Int, val value: Int)
