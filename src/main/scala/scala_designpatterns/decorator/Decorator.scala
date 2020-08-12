package scala_designpatterns.decorator

// 在以下两种情况下可以考虑使用装饰器模式：
// (1)需要在不影响其他对象的情况下，以动态、透明的方式给对象添加职责。
// (2)如果不适合使用子类来进行扩展的时候，可以考虑使用装饰器模式。

object Decorator extends App {

  trait OutStream {
    def write(b: Array[Byte])
  }

  class FileOutputStream(path: String) extends OutStream {
    override def write(b: Array[Byte]) = {
      println("do something");
    }
  }

  trait Buffering extends OutStream {
    abstract override def write(b: Array[Byte]) = {
      println("do something before super.write buffering");
      super.write(b);
    }
  }

  new FileOutputStream("hi").write("hi fileoutput stream".getBytes());

  (new FileOutputStream("hi") with Buffering).write("buffering".getBytes());
}
