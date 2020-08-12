package scala_designpatterns.mediator

/**
  * Author:BY
  * Date:2020/3/9
  * Description:
  */
object Client extends App {
  val admin: Person = new Admin("admin");
  val member1: Person = new Member("member1");
  val member2: Person = new Member("member2");
  val member3: Person = new Member("member3");
  val member4: Person = new Member("member4");

  val qqSoftware: AbstractSoftware = new QQSoftware("研发中心");
  admin.setAbstractSoftware(qqSoftware);
  member1.setAbstractSoftware(qqSoftware);
  member2.setAbstractSoftware(qqSoftware);
  member3.setAbstractSoftware(qqSoftware);
  member4.setAbstractSoftware(qqSoftware);

  admin.add(admin);
  admin.add(member1);
  admin.add(member2);
  admin.add(member3);
  admin.add(member4);
  admin.add(member1);

  admin.speak("hello");
  admin.remove(member1);

  member1.speak("hi");

  member2.add(member1);
  member2.remove(member2);

  member2.speak("admin");

  member3.privateChat(admin, "你好");
  member3.privateChat(member2, "你好");



  member2.privateChat(admin, "加我");

  println("------------------------------------------")
  val msnSoftware: AbstractSoftware = new MSNSoftware("通研事业部")
  admin.setAbstractSoftware(msnSoftware);
  member1.setAbstractSoftware(msnSoftware);
  member2.setAbstractSoftware(msnSoftware);
  member3.setAbstractSoftware(msnSoftware);
  member4.setAbstractSoftware(msnSoftware);

  admin.add(admin);
  admin.add(member1);
  admin.add(member2);
  admin.add(member3);
  admin.add(member4);
  admin.add(member1);
  admin.speak("hello");
  admin.remove(member1);

  member1.speak("hi");
  member2.add(member1);
  member2.speak("admin");
  member2.privateChat(member3,"test");
}