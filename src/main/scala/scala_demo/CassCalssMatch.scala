package scala_demo

object CassCalssMatch extends App{

  case class Student(name:String,age:Int);

  val p1=Student("Jason",19);
  val p2=Student("Tom",20);
  val p3=Student("Jimmy",21);

  p1 match {
    case Student(x,19)=>println(x);
    case Student("Tom",y)=>println(y);
    case Student(x,y)=>println(x,y);
  }
  p2 match {
    case Student(x,19)=>println(x);
    case Student("Tom",y)=>println(y);
    case Student(x,y)=>println(x,y);
  }
  p3 match {
    case Student(x,19)=>println(x);
    case Student("Tom",y)=>println(y);
    case Student(_,_)=>println(p3.name,p3.age);
  }
}
