package scala_designpatterns.abstractfactory

/**
  * Author:BY
  * Date:2019/11/28
  * Description:抽象工厂
  */
class AbstractFactory1 {
}


trait Button {
  def display(): Unit
}

trait TextField {
  def display(): Unit
}

trait SkinFactory {
  def createButton(): Button

  def createTextField(): TextField
}

object SpringSkinFactory extends SkinFactory {

  class SpringButton extends Button {
    override def display(): Unit = println("spring button")
  }

  class SpringTextField extends TextField {
    override def display(): Unit = println("spring textField")
  }

  override def createButton(): Button = new SpringButton

  override def createTextField(): TextField = new SpringTextField
}

object SummerSkinFactory extends SkinFactory {

  class SummerButton extends Button {
    override def display(): Unit = println("summer button")
  }

  class SummerTextField extends TextField {
    override def display(): Unit = println("summer textField")
  }

  override def createButton(): Button = new SummerButton

  override def createTextField(): TextField = new SummerTextField
}
