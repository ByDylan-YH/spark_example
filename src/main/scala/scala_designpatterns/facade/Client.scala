package scala_designpatterns.facade

/**
  * Author:BY
  * Date:2020/1/7
  * Description:测试客户端
  */
object Client extends App {
  val encryptFacade = new EncryptFacade
  encryptFacade.fileEncrypt("hello", "des");
}
