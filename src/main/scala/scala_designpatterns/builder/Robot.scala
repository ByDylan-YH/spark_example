package scala_designpatterns.builder

/**
  * Author:BY
  * Date:2019/12/2
  * Description:
  */
case class Robot(
                  name: String = "",
                  code:  String = "",
                  battery :Int = 0
                ){
  require(code != "", "不可缺少 code 参数");
  require(name != "", "不可缺少 name 参数");
}
