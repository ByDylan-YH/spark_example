package scala_designpatterns.facade

import java.io.{FileNotFoundException, IOException, PrintWriter}

/**
  * Author:BY
  * Date:2020/1/7
  * Description:写文件，子系统类
  */
class FileWriter {
  def write(encryptStr: String, fileNameDes: String): Unit = {
    print("保存密文，写入文件：")
    try {
      val out = new PrintWriter(fileNameDes);
      out.print(encryptStr);
      out.close();
    } catch {
      case io: IOException => io.printStackTrace();
      case noFile: FileNotFoundException => noFile.printStackTrace();
      case _: Throwable => print("其它异常");
    }
  }
}