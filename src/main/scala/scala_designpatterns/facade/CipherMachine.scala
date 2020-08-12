package scala_designpatterns.facade

/**
  * Author:BY
  * Date:2020/1/7
  * Description:加密文件，子系统类
  */
class CipherMachine {
  def encrypt(plainText: String): String = {
    println("数据加密，将明文转化为密文：");
    var es = "";
    for (i <- 0 until plainText.length) {
      es += String.valueOf(plainText.charAt(i) % 7);
    }
    print(es);
    return es;
  }
}