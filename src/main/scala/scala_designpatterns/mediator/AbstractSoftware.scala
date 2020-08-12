package scala_designpatterns.mediator

;

import scala.collection.mutable.ArrayBuffer;

/**
  * Author:BY
  * Date:2020/3/9
  * Description:中介者模式，抽象中介者，通信软件
  */
abstract class AbstractSoftware(val name: String) {
  private var persons = new ArrayBuffer[Person]();

  def addPerson(person: Person): Unit = {
    if (persons.forall(!person.eq(_))) {
      println(s"群${name}增加${person.name}");
      persons += person;
    } else {
      println(s"用户${person.name}已在群${name}内");
    }
  }

  def removePerson(person: Person): Unit = {
    if (persons.exists(person.eq(_))) {
      persons -= person;
    } else {
      println("该用户已被删除");
    }
  }

  def notify(person: Person, message: String): Unit = {
    if (persons.exists(person.eq(_))) {
      persons.filter(!person.eq(_)).foreach(p => println(s"${p.name}从${person.name}接收到信息:$message"));
    } else {
      println(s"${person.name}您已经不在群组:$name");
    }
  }

  //  私聊
  def pm(send: Person, receive: Person, message: String): Unit = send match {
    case p if persons.exists(p.eq(_)) => receive match {
      case r if persons.exists(r.eq(_)) => println(s"${send.name}发送信息:$message 给${receive.name}")
      case _ => println(s"接收者${receive.name}没有获得该软件的许可")
    }
    case _ => println(s"发送者${send.name}没有获得该软件的许可")
  }
}

//具体中介者
class QQSoftware(name: String) extends AbstractSoftware(name) {
  override def notify(person: Person, message: String): Unit = {
    println(s"这里是qq群:$name");
    super.notify(person, message);
  }

  override def pm(send: Person, receive: Person, message: String): Unit = {
    println(s"使用qq软件进行私聊");
    super.pm(send, receive, message);
  }
}

//具体中介者
class MSNSoftware(name: String) extends AbstractSoftware(name) {
  override def notify(person: Person, message: String): Unit = {
    println(s"这里是msn群:$name");
    super.notify(person, message);
  }

  override def pm(send: Person, receive: Person, message: String): Unit = {
    println(s"使用msn软件进行私聊");
    super.pm(send, receive, message);
  }
}

abstract class Person(val name: String) {
  def setAbstractSoftware(software: AbstractSoftware);

  def speak(message: String): Unit;

  def remove(person: Person): Unit;

  def add(person: Person): Unit;

  def privateChat(person: Person, message: String): Unit;
}

//管理员角色，属于同事
class Admin(name: String) extends Person(name) {
  private var abstractSoftware: AbstractSoftware = null;

  def setAbstractSoftware(software: AbstractSoftware) = abstractSoftware = software;

  override def speak(message: String) = abstractSoftware.notify(this, message);

  def remove(person: Person) = abstractSoftware.removePerson(person);

  def add(person: Person) = {
    println(s"${name}进行添加用户${person.name}的操作");
    abstractSoftware.addPerson(person);
  }

  def privateChat(person: Person, message: String) = abstractSoftware.pm(this, person, message);
}

//普通用户角色，属于同事
class Member(name: String) extends Person(name) {
  private var abstractSoftware: AbstractSoftware = null;

  def setAbstractSoftware(software: AbstractSoftware) = abstractSoftware = software;

  override def speak(message: String) = abstractSoftware.notify(this, message);

  override def add(person: Person): Unit = {
    println(s"${name}您不是管理员，不具备增加用户权限");
  }

  //  判断是否为删除自己，如果是删除自己则为退群
  override def remove(person: Person): Unit = {
    if (person.eq(this)) {
      println(s"$name，您将退出${abstractSoftware.name}");
      abstractSoftware.removePerson(person);
    } else {
      println(s"${name}您不是管理员，不具备删除用户权限");
    }
  }

  def privateChat(person: Person, message: String) = abstractSoftware.pm(this, person, message);
}