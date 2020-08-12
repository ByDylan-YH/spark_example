package scala_designpatterns.flyweight

/**
  * Author:BY
  * Date:2020/1/9
  * Description:
  */
object Client extends App {
  val factory = IgoChessmanFactory
  val black1 = factory.getIgoChessman("b")
  val black2 = factory.getIgoChessman("b")
  val black3 = factory.getIgoChessman("b")
  black1.display(Coordinates(1, 2))
  black2.display(Coordinates(1, 4))
  black3.display(Coordinates(1, 3))
  println("判断棋子是否相同：" + black1.eq(black2))

  val white1 = factory.getIgoChessman("w")
  val white2 = factory.getIgoChessman("w")
  white1.display(Coordinates(0,0))
  white2.display(Coordinates(1,1))
  println("判断棋子是否相同：" + white1.eq(white2))

  val error = factory.getIgoChessman("c")
//  错误的请求
  error.display(Coordinates(1, 0))

}