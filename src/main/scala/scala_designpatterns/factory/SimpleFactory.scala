package scala_designpatterns.factory

/**
  * Author:BY
  * Date:2019/11/28
  * Description:简单工厂模式
  */

trait IBuilding {
  def show();
}

case class SimpleBuilding(name: String) extends IBuilding {
  def show = println("SimpleBuilding " + name + " is building");
}

case class LuxuryBuilding(name: String) extends IBuilding {
  def show = println("LuxuryBuilding " + name + " is building");
}

object ConstructionFactory {
  def createBuilding(kind: String): IBuilding = kind match {
    case "Simple" => SimpleBuilding("Simple");
    case "Luxury" => LuxuryBuilding("Luxury");
  }
}

object SimpleFactory extends App {
  val simpleBuilding: IBuilding = ConstructionFactory.createBuilding("Simple");
  val luxuryBuilding: IBuilding = ConstructionFactory.createBuilding("Luxury");
  simpleBuilding.show();
  luxuryBuilding.show();
}
