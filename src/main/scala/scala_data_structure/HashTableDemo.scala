package scala_data_structure

import scala.util.control.Breaks._;


/**
 * Author:BYDylan
 * Date:2020/7/1
 * Description:
 */
object HashTableDemo {
  def main(args: Array[String]) {
    val hashTable = new HashTable(5);
    val emp1 = new Emp(1, "A");
    val emp2 = new Emp(2, "B");
    val emp3 = new Emp(3, "C");
    val emp4 = new Emp(4, "D");
    val emp5 = new Emp(5, "E");
    val emp6 = new Emp(6, "F");
    val emp7 = new Emp(7, "G");
    val emp8 = new Emp(8, "H");
    val emp9 = new Emp(9, "I");
    hashTable.addEmp(emp1);
    hashTable.addEmp(emp2);
    hashTable.addEmp(emp3);
    hashTable.addEmp(emp4);
    hashTable.addEmp(emp5);
    hashTable.addEmp(emp6);
    hashTable.addEmp(emp7);
    hashTable.addEmp(emp8);
    hashTable.addEmp(emp9);
    hashTable.list();
  }
}

//编写hashtable类
class HashTable(size: Int) {
  //  维护一个EmpLinkedList 数组
  val empLinkedListArr = new Array[EmpLinkedList](size);
  for (i <- 0 until size) {
    empLinkedListArr(i) = new EmpLinkedList();
  }

  //  散列函数
  def hash(no: Int): Int = {
    no % size;
  }

  //  添加
  def addEmp(emp: Emp): Unit = {
    val empLinkedListNo = hash(emp.no);
    empLinkedListArr(empLinkedListNo).addEmp(emp);
  }

  def list(): Unit = {
    for (i <- 0 until size) {
      empLinkedListArr(i).list(i);
    }
  }
}


class Emp(empNo: Int, eName: String) {
  val no = empNo;
  val name = eName;
  var next: Emp = null;
}

//编写EmpLinkedList,存放的是雇员信息
class EmpLinkedList {
  var head: Emp = null;

  //  添加雇员
  //  直接加入到链表最后
  def addEmp(emp: Emp): Unit = {
    if (head == null) {
      head = emp;
    } else {
      //    使用辅助指针来完成
      var curEmp = head;
      //    将curEmp 定位到链表的最后
      breakable {
        while (true) {
          if (curEmp.next == null) {
            break();
          }
          curEmp = curEmp.next;
        }
      }
      curEmp.next = emp;
    }
  }

  //  遍历该链表
  def list(no: Int): Unit = {
    //    判断链表是否为null
    if (head == null) {
      print("第" + no + "链表情况:\n");
      return
    }
    //    使用辅助指针
    var curEmp = head;
    print("第" + no + "链表情况:\n");
    breakable {
      while (true) {
        printf("no=%d -> name=%s\n", curEmp.no, curEmp.name);
        if (curEmp.next == null) {
          break();
        }
        curEmp = curEmp.next;
      }
    }
  }
}