package scala_data_structure

import java.util;

import scala.util.control.Breaks._;

/**
 * Author:BYDylan
 * Date:2020/6/15
 * Description:
 */
object SingleLinkedListDemo {
  def main(args: Array[String]) {
    val singleLinkedList = new SingleLinkedList();
    val node1 = new HeroNode(1, "key1", "vaule1");
    val node2 = new HeroNode(2, "key2", "vaule2");
    val node3 = new HeroNode(3, "key3", "vaule3");
    val node4 = new HeroNode(4, "key4", "vaule4");
    singleLinkedList.add(node1);
    singleLinkedList.add(node2);
    singleLinkedList.add(node4);
    val node5 = new HeroNode(4, "修改", "vaule3");
    singleLinkedList.update(node5);
    singleLinkedList.delete(1);
    singleLinkedList.delete(12);
    singleLinkedList.addByOrder(node3);
    println(singleLinkedList.list());
//    逆序打印
    singleLinkedList.reverse();
  }
}

//创建单链表的类
class SingleLinkedList {
  //  创建头结点,指向该链表的头部
  val head = new HeroNode(-1, "", "");

  //  使用栈,从尾到头打印单链表,同时不破坏链表本身的结构
  //  1.遍历单向链表,将节点push到stack
  //  2.遍历stack,取出节点
  //  注意:在操作中没有创建新对象,只是使用引用
  def reverse(): Unit = {
    if (isEmpty()) {
      println("链表为空,无法遍历");
      return;
    }
    //  创建一个栈,
    val stack = new util.Stack[HeroNode]();
    var temp: HeroNode = head.next;
    //  遍历单向链表,将节点push到stack中
   breakable {
      while (true) {
        stack.push(temp);
        if (temp.next == null) {
          break();
        }
        temp = temp.next;
      }
    }
while (!stack.empty()){
  val heroNode: HeroNode = stack.pop();
  printf("no=%d -> name=%s\n",heroNode.no,heroNode.name);
}

  }

  //  添加到单链表
  //  默认加入到链表尾部
  def add(heroNode: HeroNode): Unit = {
    //  1.先找到链表最后的结点
    //  2.然后让最后结点.next = 新的结点
    //  因为head不动,因此使用一个辅助指针来进行定位
    var temp = head;
    breakable {
      while (true) {
        if (temp.next == null) {
          break();
        }
        //      后移
        temp = temp.next;
      }
    }
    temp.next = heroNode;
  }

  def addByOrder(heroNode: HeroNode): Unit = {
    //    让temp指向head
    var temp = head;
    //    标识是否已经存在这个编号的结点
    var flag = false;
    //    将temp定位到要添加的结点的前一位置,即将新的结点添加到temp后面
    breakable {
      while (true) {
        //        判断是否已经到最后一个
        if (temp.next == null) {
          break();
        }
        if (temp.next.no == heroNode.no) {
          flag = true;
          break();
        } else if (temp.next.no > heroNode.no) { // heronode应该添加到temp的后面
          break();
        }
        temp = temp.next;
      }
    }
    if (flag) {
      printf("已经存在结点: %d\n", heroNode.no);
    } else {
      heroNode.next = temp.next;
      temp.next = heroNode;
    }
  }

  //  遍历单向链表
  //  1.仍然让temp帮助进行遍历
  //  2.判断链表是否为空,空退出,不空就遍历,直到最后的结点
  def list(): Unit = {
    if (isEmpty()) {
      println("链表为空,无法遍历");
      return;
    }
    var temp = head.next;
    breakable {
      while (true) {
        //输出当前结点的信息
        printf("no= %d,name = %s,nickname= %s \n", temp.no, temp.name, temp.nickName);
        //      是否是最后结点
        if (temp.next == null) {
          break();
        }
        temp = temp.next;
      }
    }
  }

  def update(heroNode: HeroNode): Unit = {
    if (isEmpty()) {
      println("链表为空,无法修改");
      return;
    }
    //    辅助指针,帮助定位修改的结点
    var temp = head.next;
    //    需要定义一个变量,标识是否找到该节点
    var flag = false;
    breakable {
      while (true) {
        if (temp.no == heroNode.no) {
          flag = true;
          break();
        }
        //        判断temp是否到最后,
        if (temp.next == null) {
          break();
        }
        temp = temp.next;
      }
    }
    if (flag) {
      temp.nickName = heroNode.nickName;
      temp.name = heroNode.name;
    } else {
      printf("未找到结点: %d\n", heroNode.no);
    }
  }

  def delete(no: Int): Unit = {
    if (isEmpty()) {
      println("链表为空,无法删除");
      return;
    }
    var temp = head;
    var flag = false;
    //    遍历,让temp指向要删除节点的前一个结点
    breakable {
      while (true) {
        if (temp.next.no == no) {
          flag = true;
          break();
        }
        //      判断temp是否指向链表的倒数第二个结点
        if (temp.next.next == null) {
          break();
        }
        temp = temp.next;
      }
    }
    if (flag) {
      temp.next = temp.next.next;
      printf("已删除结点: %d\n", no);
    } else {
      printf("未找到结点: %d\n", no);
    }
  }

  def isEmpty(): Boolean = {
    head.next == null;
  }
}

class HeroNode(hNo: Int, hName: String, hNickName: String) {
  var no = hNo;
  var name = hName;
  var nickName = hNickName;
  //  默认为null
  var next: HeroNode = null;
}