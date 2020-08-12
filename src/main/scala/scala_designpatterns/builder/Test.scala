package scala_designpatterns.builder

/**
  * Author:BY
  * Date:2019/12/2
  * Description:
  */
object Test extends App {
  val robot1 = Robot(
    code = "89757",
    name = "Bat-Man",
    battery = 99
  );
  System.out.println(s"Robot1 : $robot1");
  try {
    val robot2 = Robot(name = "Bat-Man");
  } catch {
    case e: Throwable => e.getMessage();
  }
}
