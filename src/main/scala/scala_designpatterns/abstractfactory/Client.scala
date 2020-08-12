package scala_designpatterns.abstractfactory

/**
  * Author:BY
  * Date:2019/11/28
  * Description:抽象工厂调用
  */
object Client extends App {
  val factory: SkinFactory = SpringSkinFactory;
  factory.createButton().display();
  factory.createTextField().display();
}
