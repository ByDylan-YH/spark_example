package scala_designpatterns.adapter

/**
  * Author:BY
  * Date:2019/12/9
  * Description:
  */
// 现有的标准电源,输出220电压
class PowerSource {
  val outPut = "220V电压";
}


// 需要的电源
trait Charger {
  def get5V: String;

  def get10V: String;
}


// 隐士转换类（变压器） 将标准电压转为输出电压，需要什么就重写什么方法即可
object Transformer {
  implicit def swap(p: PowerSource): Charger = new Charger {
    override def get5V: String = ???;

    override def get10V: String = p.outPut + ",已经转为10V,可以给电脑充电了";
  }
}

// 客户端调用
object Client {
  def main(args: Array[String]): Unit = {

    import Transformer._;
    val source = new PowerSource();
    println(source.get10V);
  }
}


