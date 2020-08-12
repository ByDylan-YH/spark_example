package scala_data_structure

import scala.util.control.Breaks._;

/**
 * Author:BYDylan
 * Date:2020/6/15
 * Description:
 */
object DoubleLinkedListDemo {
  def main(args: Array[String]) {
    val doubleLinkedList = new DoubleLinkedList();
    val node1 = new DoubleHeroNode(1, "key1", "vaule1");
    val node2 = new DoubleHeroNode(2, "key2", "vaule2");
    val node3 = new DoubleHeroNode(3, "key3", "vaule3");
    val node4 = new DoubleHeroNode(4, "key4", "vaule4");
    doubleLinkedList.add(node1);
    doubleLinkedList.add(node2);
    doubleLinkedList.add(node4);
    val node5 = new DoubleHeroNode(4, "修改后", "vaule4");
    doubleLinkedList.update(node5);
    doubleLinkedList.del(4);
    println(doubleLinkedList.list());
  }
}

class DoubleLinkedList {
  //  创建头结点,指向该链表的头部
  val head = new DoubleHeroNode(-1, "", "");

  //  添加到单链表
  //  默认加入到链表尾部
  def add(doubleHeroNode: DoubleHeroNode): Unit = {
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
    temp.next = doubleHeroNode;
    //    必须有
    doubleHeroNode.pre = temp;
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

  def update(heroNode: DoubleHeroNode): Unit = {
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

  def del(no: Int): Unit = {
    if (isEmpty()) {
      println("链表为空");
      return
    }
    var temp = head.next;
    var flag = false;
    breakable {
      while (true) {
        if (temp.no == no) {
          flag = true;
          break();
        }
        if (temp.next == null) {
          break();
        }
        temp = temp.next;
      }
    }
    //    当退出循环后,flag=true,temp指向要删除的结点
    if (flag) {
      temp.pre.next = temp.next;
      if (temp.next != null) {
        temp.next.pre = temp.pre;
      };
    } else {
      printf("未找到结点: %d\n", no);
    }
  }

  def isEmpty(): Boolean = {
    head.next == null;
  }
}

class DoubleHeroNode(hNo: Int, hName: String, hNickName: String) {
  var no = hNo;
  var name = hName;
  var nickName = hNickName;
  //  指向后一个结点,默认为null
  var next: DoubleHeroNode = null;
  //  指向前一个结点
  var pre: DoubleHeroNode = null;
}