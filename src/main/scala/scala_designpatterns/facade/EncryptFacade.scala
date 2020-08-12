package scala_designpatterns.facade

/**
  * Author:BY
  * Date:2020/1/7
  * Description:加密外观类
  */
class EncryptFacade {
  private val fileReader = new FileReader;
  private val cipherMachine = new CipherMachine;
  private val fileWriter = new FileWriter;

  def fileEncrypt(fileNameSrc: String, fileNameDes: String): Unit = {
    fileWriter.write(cipherMachine.encrypt(fileReader.read(fileNameSrc)), fileNameDes);
  }
}
