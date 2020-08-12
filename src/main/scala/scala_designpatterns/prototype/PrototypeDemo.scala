package scala_designpatterns.prototype

/**
  * Author:BY
  * Date:2019/12/3
  * Description:原型模式
  */
case class Cell(dna: String, organelle: Organelle);

case class Organelle(cytoplasm: String, nucleus: String);

object PrototypeDemo extends App {
  val initialCell = Cell("DNA", Organelle("细胞质", "细胞核"));
  val cell1 = initialCell.copy();
  val cell2 = initialCell.copy();
  val cell3 = initialCell.copy(dna = "1234"); // 可以在拷贝的时候重新赋值
  println(s"cell1: ${cell1}");
  println(s"cell2: ${cell2}");
  println(s"cell3: ${cell3}");
  println(s"cell1 and cell2 are equal: ${cell1 == cell2}");
}

