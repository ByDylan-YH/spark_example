package scala_designpatterns.flyweight

import scala.collection.mutable;

/**
  * 坐标样例类
  *
  * @param x x坐标
  * @param y y坐标
  */
case class Coordinates(x: Int, y: Int)

/**
  * 抽象享元类
  */
abstract class IgoChessman {
  /**
    * 获得棋子的颜色
    *
    * @return 棋子颜色
    */
  def color(): String

  /**
    * 展示方法
    *
    * @param coordinates 坐标样例类
    */
  def display(coordinates: Coordinates): Unit = {
    /**
      * 如果没有颜色就打印没有颜色
      */
    if (color() == "没有该颜色的棋子") {
      println(color())
    } else {
      println("棋子颜色：" + color() + "棋子位置：" + coordinates.x + "," + coordinates.y)
    }

  }
}

//黑色棋子
object BlackIgoChessman extends IgoChessman {
  override def color() = "黑色\t"
}

//白色棋子
object WhiteIgoChessman extends IgoChessman {
  override def color() = "白色\t"
}

//享元工厂类
object IgoChessmanFactory {
  //  存放享元对象的HashMap
  private val hm = new mutable.HashMap[String, IgoChessman] {}
  //  黑色棋子
  val black: IgoChessman = BlackIgoChessman
  //  白色棋子
  val white: IgoChessman = WhiteIgoChessman
  hm.put("b", black)
  hm.put("w", white)

  /**
    * 获取棋子
    *
    * @param color 颜色
    * @return 棋子
    */
  def getIgoChessman(color: String): IgoChessman = hm.getOrElse(color, new IgoChessman {
    override def color(): String = "没有该颜色的棋子"
  })
}
