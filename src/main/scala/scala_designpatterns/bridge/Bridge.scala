package scala_designpatterns.bridge

/**
  * Author:BY
  * Date:2019/12/17
  * Description:桥接模式
  */

// 1.颜色
trait Color {
  def drawShape(`type`: String);
}

class Red extends Color{
  override def drawShape(`type`: String) = println(s"Red ${`type`} is drawn");
}

class Blue extends Color{
  override def drawShape(`type`: String) = println(s"Blue ${`type`} is drawn");
}

class Yellow extends Color{
  override def drawShape(`type`: String) = println(s"Yellow ${`type`} is drawn");
}

// 2.形状
abstract class Shape(color: Color) {
  def draw();
}

class ShapeI(color: Color) extends Shape(color){
  override def draw(): Unit = color.drawShape("ShapeI");
}

class ShapeJ(color: Color) extends Shape(color){
  override def draw(): Unit = color.drawShape("ShapeJ");
}

object Bridge extends App{
  new ShapeI(new Blue).draw();
  new ShapeJ(new Red).draw();
}
