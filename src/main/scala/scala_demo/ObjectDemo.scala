package scala_demo

object ObjectDemo extends App {
  val x = 0;
  val y = 0;

  def sayHello(): Unit = {

  }

  def apply(ac: Int, bc: Int): ObjectDemo = new ObjectDemo(ac, bc)

  val obj = ObjectDemo(1, 2);
  println(obj.a);

}

class ObjectDemo(ac: Int, bc: Int) {
  private val a = ac;
  val b = bc;

  def unapply(arg: ObjectDemo): Option[(Int, Int)] = {
    if (arg == null)
      None;
    else
      Some(arg.a, arg.b);
  }
}

